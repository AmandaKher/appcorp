<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pedido</title>
    <style>
        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        label, input, select {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        input, select {
            padding: 8px;
        }
        button {
            padding: 10px;
            width: 100%;
            background: #007bff;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Cadastro de Pedido</h2>
    <form action="PedidoServlet" method="post">
        <input type="hidden" name="action" value="inserir">

        <label for="data">Data:</label>
        <input type="date" name="data" id="data" required>

        <label for="status">Status:</label>
        <input type="text" name="status" id="status" required>

        <label for="valorTotal">Valor Total:</label>
        <input type="number" name="valorTotal" id="valorTotal" step="0.01" required>

        <label for="produtos">Produtos:</label>
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

        <button type="submit">Cadastrar Pedido</button>
    </form>
</body>
</html>