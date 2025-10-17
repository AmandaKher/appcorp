<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Pedido" %>
<%@ page import="br.cefetrj.model.Produto" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Pedidos</title>
    <style>
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: center;
        }
        td {
            text-align: center;
        }
        .produto-list {
            list-style: none;
            padding-left: 0;
        }
    </style>
</head>
<body>
    <h1 style="text-align:center;">Lista de Pedidos</h1>

    <%
        // Recebe a lista de pedidos do servlet
        List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
        if (pedidos == null || pedidos.isEmpty()) {
    %>
        <p style="text-align:center;">Nenhum pedido encontrado.</p>
    <%
        } else {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID Pedido</th>
                    <th>Data</th>
                    <th>Status</th>
                    <th>Valor Total</th>
                    <th>Produtos</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Pedido pedido : pedidos) {
            %>
                <tr>
                    <td><%= pedido.getId() %></td>
                    <td><%= pedido.getData() %></td>
                    <td><%= pedido.getStatus() %></td>
                    <td>R$ <%= String.format("%.2f", pedido.getValorTotal()) %></td>
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