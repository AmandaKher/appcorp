<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Produto" %>
<%@ page import="br.cefetrj.model.Pedido" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   Pedido pedido = (Pedido) request.getAttribute("entidade");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pedido</title>
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
    <h2><% if (pedido != null) { %>Editar Pedido<% } else { %>Cadastrar Pedido<% } %></h2>
    <form action="<%= request.getAttribute("urlSubmit") %>" method="post">
        <input type="hidden" name="id" value="<%= pedido != null ? pedido.getId() : "" %>">
        <input type="hidden" name="acao" value="<%= pedido != null ? "editar" : "cadastrar" %>">

        <label for="data">Data:</label>
        <input type="date" id="data" name="data" value="<%= pedido != null ? pedido.getData() : "" %>" required><br><br>

        <label for="produto">Produtos:</label>
        <select name="produtoIds" id="produtos" multiple size="5" required>
            <% 
                // Recupera a lista de produtos do request
                List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                if (produtos != null) {
                    for (Produto produto : produtos) {
            %>
                        <option value="<%= produto.getId() %>">
                            <%= produto.getNome() %> - <%= produto.getTamanho() %> - <%= produto.getCor() %>
                        </option>
            <%
                    }
                }
            %>
        </select>
        <p>Use Ctrl ou Shift para selecionar múltiplos produtos</p>

        <button type="submit"><%= pedido != null ? "Editar Pedido" : "Cadastrar Pedido" %></button>
    </form>
</body>
</html>