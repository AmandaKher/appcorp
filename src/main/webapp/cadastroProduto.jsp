<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Produto</title>
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
    <h2 style="text-align:center;">Cadastro de Produto</h2>
    <form action="ProdutoServlet" method="post">
        
        <input type="hidden" name="action" value="inserir">
        
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" required>

        <label for="tamanho">Tamanho:</label>
        <input type="text" name="tamanho" id="tamanho" required>

        <label for="cor">Cor:</label>
        <input type="text" name="cor" id="cor" required>

        <label for="preco">Preço:</label>
        <input type="number" step="0.01" name="preco" id="preco" required>

        <button type="submit">Cadastrar Produto</button>
    </form>
</body>
</html>