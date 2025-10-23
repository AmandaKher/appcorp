<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Produto" %>
<%
   Produto produto = (Produto) request.getAttribute("entidade");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Produto</title>
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
    <h2><% if (produto != null) { %>Editar Produto<% } else { %>Cadastrar Produto<% } %></h2>
    <form action="<%= request.getAttribute("urlSubmit") %>" method="post">
        <input type="hidden" name="id" value="<%= produto != null ? produto.getId() : "" %>">
        <input type="hidden" name="acao" value="<%= produto != null ? "editar" : "cadastrar" %>">

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%= produto != null ? produto.getNome() : "" %>" required><br><br>

        <label for="cor">Cor:</label>
        <input type="text" id="cor" name="cor" value="<%= produto != null ? produto.getCor() : "" %>" required><br><br>

        <label for="tamanho">Tamanho:</label>
        <input type="text" id="tamanho" name="tamanho" value="<%= produto != null ? produto.getTamanho() : "" %>" required><br><br>

        <label for="preco">Preço:</label>
        <input type="text" id="preco" name="preco" value="<%= produto != null ? produto.getPreco() : "" %>" required><br><br>

        <button type="submit"><%= produto != null ? "Editar Produto" : "Cadastrar Produto" %></button>
</body>
</html>