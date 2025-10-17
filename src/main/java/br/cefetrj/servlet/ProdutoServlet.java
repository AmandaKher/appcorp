package br.cefetrj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.ProdutoDao;
import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    private Connection getConnection() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projeto", // ajuste para seu banco
                    "root", // usuário
                    "" // senha
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Erro ao conectar no banco", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try (Connection con = getConnection()) {
            ProdutoDao dao = new ProdutoDao(con);

            if ("listar".equals(action)) {
                List<Produto> produtos = dao.getAll();
                request.setAttribute("produtos", produtos);
                request.getRequestDispatcher("listaProduto.jsp").forward(request, response);

            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
                response.sendRedirect("ProdutoServlet?action=listar");

            } else if ("cadastro".equals(action)) {

                request.getRequestDispatcher("cadastroProduto.jsp").forward(request, response);

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Produto produto = dao.getById(id);
                request.setAttribute("produto", produto);
                request.getRequestDispatcher("editarProduto.jsp").forward(request, response);

            } else {
                response.getWriter().println("Ação inválida para GET");
            }

        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro no processamento", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        try (Connection con = getConnection()) {
            ProdutoDao dao = new ProdutoDao(con);

            if ("inserir".equals(action)) {
                Produto produto = new Produto();
                produto.setNome(request.getParameter("nome"));
                produto.setCor(request.getParameter("cor"));
                produto.setTamanho(request.getParameter("tamanho"));
                produto.setPreco(Double.valueOf(request.getParameter("preco")));

                dao.insert(produto); // insere no banco
                List<Produto> produtos = dao.getAll(); // busca lista atualizada
                request.setAttribute("produtos", produtos); // coloca no request
                request.getRequestDispatcher("listaProduto.jsp").forward(request, response);

            } else if ("atualizar".equals(action)) {
                Produto produto = new Produto();
                int id = Integer.parseInt(request.getParameter("id"));
                produto.setId(id);
                produto.setNome(request.getParameter("nome"));
                produto.setCor(request.getParameter("cor"));
                produto.setTamanho(request.getParameter("tamanho"));
                produto.setPreco(Double.valueOf(request.getParameter("preco")));

                dao.alterar(produto);
                response.sendRedirect("ProdutoServlet?action=listar");

            } else {
                response.getWriter().println("Ação inválida para POST");
            }
        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro ao operar com cliente", e);
        }
    }
    /*
     * protected void doPost(HttpServletRequest request, HttpServletResponse
     * response) throws IOException {
     * response.setContentType("text/html");
     * response.setCharacterEncoding("UTF-8");
     * response.getWriter().println("<html><body>");
     * response.getWriter().println("Id: " + request.getParameter("id") + "<br>");
     * response.getWriter().println("Nome: " + request.getParameter("nome") +
     * "<br>");
     * response.getWriter().println("Tamanho: " + request.getParameter("tamanho") +
     * "<br>");
     * response.getWriter().println("Cor: " + request.getParameter("cor") + "<br>");
     * response.getWriter().println("Preço: " + request.getParameter("preco") +
     * "<br>");
     * response.getWriter().println("Estoque: " + request.getParameter("estoque") +
     * "<br>");
     * response.getWriter().println("</body></html>");
     * }
     * 
     * protected void doGet(HttpServletRequest request, HttpServletResponse
     * response)
     * throws IOException, ServletException {
     * List<Produto> produtos = criaProduto();
     * request.setAttribute("produtos", produtos);
     * RequestDispatcher rd = request.getRequestDispatcher("listaProduto.jsp");
     * 
     * rd.forward(request, response);
     * }
     * 
     * private List<Produto> criaProduto() {
     * List<Produto> produtos = new ArrayList<>();
     * Produto produto1 = new Produto(1, "calça", "36", "jeans", 250.00, 80);
     * Produto produto2 = new Produto(2, "camisa", "M", "preta", 100.00, 50);
     * Produto produto3 = new Produto(3, "shorts", "36", "verde", 80.00, 20);
     * Produto produto4 = new Produto(4, "camisa", "P", "marrom", 90.00, 35);
     * produtos.add(produto1);
     * produtos.add(produto2);
     * produtos.add(produto3);
     * produtos.add(produto4);
     * return produtos;
     * }
     */
}
