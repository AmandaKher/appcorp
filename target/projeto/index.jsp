<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Menu Principal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .menu-container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 12px rgba(0,0,0,0.15);
            text-align: center;
            width: 350px;
        }
        h1 {
            margin-bottom: 25px;
            color: #333;
        }
        a {
            display: block;
            padding: 12px;
            margin: 10px 0;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-size: 16px;
        }
        a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="menu-container">
        <h1>Menu Principal</h1>
        <a href="cadastroVendedor.jsp">Cadastrar Vendedor</a>
        <a href="VendedorServlet?action=listar">Listar Vendedores</a>
    </div>
</body>
</html>
