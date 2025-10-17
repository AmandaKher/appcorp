<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.cefetrj.model.Cliente" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    if (cliente == null) {
        response.sendRedirect("ClienteServlet?action=listar");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>
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
    <h2 style="text-align:center;">Editar Cliente</h2>
    <form action="ClienteServlet" method="post">
        
        <input type="hidden" name="action" value="atualizar">
        <input type="hidden" name="id" value="<%= cliente.getId() %>">

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= cliente.getNome() %>" required>

        <label for="cpf">CPF:</label>
        <input type="cpf" name="cpf" id="cpf" value="<%= cliente.getCpf() %>" required>

        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone" id="telefone" value="<%= cliente.getTelefone() %>" required>

        <button type="submit">Salvar Alterações</button>
    </form>
</body>
</html>