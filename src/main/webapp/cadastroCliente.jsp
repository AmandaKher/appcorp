<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Cliente</title>
    <style>
        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
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
    <h2 style="text-align:center;">Cadastro de Cliente</h2>
    <form action="ClienteServlet" method="post">

        <input type="hidden" name="action" value="inserir">
        
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" required>

        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" required>

        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone" id="telefone" required>

        <button type="submit">Cadastrar Cliente</button>
    </form>
</body>
</html>