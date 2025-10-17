<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Produto" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #333;
            padding: 8px;
            text-align: center;
        }
        th {
            background: #eee;
        }
    </style>
</head>
<body>
     <h2 style="text-align:center;">Produtos</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Tamanho</th>
            <th>Cor</th>
            <th>Preço</th>
        </tr>
        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null) {
                for (Produto p : produtos) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNome() %></td>
            <td><%= p.getTamanho() %></td>
            <td><%= p.getCor() %></td>
            <td><%= p.getPreco() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="6">Nenhum produto encontrado</td></tr>
        <%
            }
        %>
    </table>
</body>
</html>