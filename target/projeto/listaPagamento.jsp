<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Pagamento" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Pagamentos</title>
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
    <h2 style="text-align:center;">Pagamentos</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Valor</th>
                <th>Data</th>
                <th>Forma de Pagamento</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Pagamento> pagamentos = (List<Pagamento>) request.getAttribute("pagamentos");
                if (pagamentos != null && !pagamentos.isEmpty()) {
                    for (Pagamento pagamento : pagamentos) {
            %>
                        <tr>
                            <td><%= pagamento.getIdPagamento() %></td>
                            <td><%= pagamento.getValor() %></td>
                            <td><%= pagamento.getData() %></td>
                            <td><%= pagamento.getForma() %></td>
                            <td><%= pagamento.getStatus() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                        <tr>
                            <td colspan="6">Nenhum pagamento encontrado</td>
                        </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>