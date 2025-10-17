<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Cliente" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
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
    <h2 style="text-align:center;">Clentes Cadastrados</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Telefone</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                if (clientes != null && !clientes.isEmpty()) {
                    for (Cliente cliente : clientes) {
            %>
                        <tr>
                            <td><%= cliente.getId() %></td>
                            <td><%= cliente.getNome() %></td>
                            <td><%= cliente.getCpf() %></td>
                            <td><%= cliente.getTelefone() %></td>
                            <td>
                                <a href="ClienteServlet?action=editar&id=<%= cliente.getId() %>">Editar</a> | 
                                <a href="ClienteServlet?action=excluir&id=<%= cliente.getId() %>" 
                                    onclick="return confirm('Tem certeza que deseja excluir este cliente?');">Excluir</a>
                            </td>
                        </tr>
                        </tr>
            <%
                    }
                } else {
            %>
                        <tr>
                            <td colspan="6">Nenhum cliente encontrado</td>
                        </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>