<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Pedido" %>
<%@ page import="br.cefetrj.model.Produto" %>
<%

    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Pedidos</title>
    <style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f5f6fa;
        color: #333;
        margin: 0;
        padding: 0;
    }

    h2 {
        text-align: center;
        margin-top: 30px;
        color: #2f3640;
    }

    a.button {
        display: inline-block;
        margin: 20px auto;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: background 0.3s;
    }

    a.button:hover {
        background-color: #45a049;
        text-align: center;
    }

    table {
        border-collapse: collapse;
        width: 90%;
        max-width: 900px;
        margin: 20px auto;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        background-color: #fff;
        border-radius: 8px;
        overflow: hidden;
    }

    th, td {
        padding: 12px 15px;
        text-align: center;
    }
    
    th {
        background-color: #3498db;
        color: white;
        font-weight: bold;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #dfe6e9;
        transition: background 0.3s;
    }

    td a {
        color: #3498db;
        text-decoration: none;
        margin: 0 5px;
        font-weight: bold;
    }

    td a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <h2>Lista de Pedidos</h2>
    <div style="text-align: center; margin-bottom: 20px;">
    <a href="<%= request.getAttribute("urlSubmit") %>?acao=novo" class="button">
        Cadastrar Novo Pedido
    </a>
</div>
    <table>
        <thead>
            <tr>
                <th>ID Pedido</th>
                <th>Data</th>
                <th>Produtos</th>
                <th>Ações</th>
            </tr>
        </thead>
        <%
            if (pedidos == null || pedidos.isEmpty()){
        %>
            <p style="text-align:center;">Nenhum pedido encontrado.</p>
        <%
            } else {
        %>
            <tbody>
            <%
                for (Pedido pedido : pedidos) {
            %>
                <tr>
                    <td><%= pedido.getId() %></td>
                    <td><%= pedido.getData() %></td>
                    <td>
                        <ul class="produto-list">
                        <%
                            List<Produto> produtos = pedido.getProdutos();
                            if (produtos != null && !produtos.isEmpty()) {
                                for (Produto produto : produtos) {
                        %>
                            <li>
                                <strong>Nome:</strong> <%= produto.getNome() %>, 
                                <strong>Tamanho:</strong> <%= produto.getTamanho() %>, 
                                <strong>Cor:</strong> <%= produto.getCor() %>
                            </li>
                        <%
                                }
                            } else {
                        %>
                            <li>Nenhum produto</li>
                        <%
                            }
                        %>
                        </ul>
                    </td>
                    <td>
                        <a href="<%= request.getAttribute("urlSubmit") %>?acao=buscar&id=<%= pedido.getId() %>">Editar</a>
                        <a href="<%= request.getAttribute("urlSubmit") %>?acao=deletar&id=<%= pedido.getId() %>" onclick="return confirm('Tem certeza que deseja remover?');">Remover</a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
    </table>
    <%
        }
    %>
</body>
</html>