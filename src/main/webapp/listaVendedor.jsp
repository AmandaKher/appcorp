<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Vendedor" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Vendedores</title>
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
    <h2 style="text-align:center;">Vendedores Cadastrados</h2>
    <table>
        <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Telefone</th>
        <th>Limite de Desconto</th>
        <th>Ações</th>
    </tr>
</thead>
<tbody>
    <%
        List<Vendedor> vendedores = (List<Vendedor>) request.getAttribute("vendedores");
        if (vendedores != null && !vendedores.isEmpty()) {
            for (Vendedor vendedor : vendedores) {
    %>
        <tr>
            <td><%= vendedor.getId() %></td>
            <td><%= vendedor.getNome() %></td>
            <td><%= vendedor.getEmail() %></td>
            <td><%= vendedor.getTelefone() %></td>
            <td><%= vendedor.getLimiteDesconto() %></td>
            <td>
                <a href="VendedorServlet?action=editar&id=<%= vendedor.getId() %>">Editar</a> | 
                <a href="VendedorServlet?action=excluir&id=<%= vendedor.getId() %>" 
                   onclick="return confirm('Tem certeza que deseja excluir este vendedor?');">Excluir</a>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="6">Nenhum vendedor encontrado</td>
        </tr>
    <%
        }
    %>
</tbody>
    </table>
</body>
</html>