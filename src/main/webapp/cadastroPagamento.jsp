<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Pagamento</title>
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
    <h2 style="text-align:center;">Cadastro de Pagamento</h2>
    <form action="PagamentoServlet" method="post">

        <input type="hidden" name="action" value="inserir">
        
        <label for="valor">Valor</label>
        <input type="text" name="valor" id="valor" required>

        <label for="data">Data:</label>
        <input type="date" name="data" id="data" required>

        <label for="forma">Forma de Pagamento:</label>
        <input type="text" name="forma" id="forma" required>

        <button type="submit">Cadastrar Pagamento</button>
    </form>
</body>
</html>