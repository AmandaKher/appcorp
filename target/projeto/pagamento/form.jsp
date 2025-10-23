<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Pagamento" %>
<%
   Pagamento pagamento = (Pagamento) request.getAttribute("entidade");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pagamento</title>
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

    form {
        width: 90%;
        max-width: 500px;
        margin: 30px auto;
        background-color: #fff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
        color: #2f3640;
    }

    input[type="text"],
    input[type="number"],
    input[type="date"],
    input[type="email"],
    select {
        width: 100%;
        padding: 10px 12px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 6px;
        box-sizing: border-box;
        transition: border 0.3s, box-shadow 0.3s;
    }

    input[type="text"]:focus,
    input[type="number"]:focus,
    input[type="date"]:focus,
    input[type="email"]:focus,
    select:focus {
        border-color: #3498db;
        box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
        outline: none;
    }

    .btn-submit {
        display: inline-block;
        width: 100%;
        padding: 12px;
        background-color: #4CAF50;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: background 0.3s;
    }

    .btn-submit:hover {
        background-color: #45a049;
    }

    .btn-cancel {
        display: inline-block;
        width: 100%;
        padding: 12px;
        margin-top: 10px;
        background-color: #e74c3c;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        transition: background 0.3s;
    }

    .btn-cancel:hover {
        background-color: #c0392b;
    }
</style>
</head>
<body>
    <h2><% if (pagamento != null) { %>Editar Pagamento<% } else { %>Cadastrar Pagamento<% } %></h2>
    <form action="<%= request.getAttribute("urlSubmit") %>" method="post">
        <input type="hidden" name="id" value="<%= pagamento != null ? pagamento.getId() : "" %>">
        <input type="hidden" name="acao" value="<%= pagamento != null ? "editar" : "cadastrar" %>">

        <label for="valor">Valor:</label>
        <input type="text" id="valor" name="valor" value="<%= pagamento != null ? pagamento.getValor() : "" %>" required><br><br>

        <label for="DataPagamento">Data:</label>
        <input type="date" id="DataPagamento" name="DataPagamento" value="<%= pagamento != null ? pagamento.getDataPagamento() : "" %>" required><br><br>

        <label for="forma">Forma de Pagamento:</label>
        <input type="text" id="forma" name="forma" value="<%= pagamento != null ? pagamento.getForma() : "" %>" required><br><br>

        <button type="submit"><%= pagamento != null ? "Editar Pagamento" : "Cadastrar Pagamento" %></button>
</body>
</html>