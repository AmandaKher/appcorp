<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.cefetrj.model.Vendedor" %>
<%
    Vendedor vendedor = (Vendedor) request.getAttribute("vendedor");
    if (vendedor == null) {
        response.sendRedirect("VendedorServlet?action=listar");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Vendedor</title>
    <style>
        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background: #fff;
        }
        label, input {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        input {
            padding: 8px;
        }
        button {
            padding: 10px;
            width: 100%;
            background: #28a745;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Editar Vendedor</h2>
    <form action="VendedorServlet" method="post">
        
        <input type="hidden" name="action" value="atualizar">
        <input type="hidden" name="id" value="<%= vendedor.getId() %>">

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= vendedor.getNome() %>" required>

        <label for="email">E-mail:</label>
        <input type="email" name="email" id="email" value="<%= vendedor.getEmail() %>" required>

        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone" id="telefone" value="<%= vendedor.getTelefone() %>" required>

        <label for="limiteDesconto">Limite de Desconto:</label>
        <input type="number" step="0.01" name="limiteDesconto" id="limiteDesconto" value="<%= vendedor.getLimiteDesconto() %>" required>

        <button type="submit">Salvar Alterações</button>
    </form>
</body>
</html>