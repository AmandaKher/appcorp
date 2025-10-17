package br.cefetrj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.dao.PedidoDao;
import br.cefetrj.dao.ProdutoDao;
import br.cefetrj.exception.DaoException;

import br.cefetrj.model.Pedido;
import br.cefetrj.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {

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
            PedidoDao dao = new PedidoDao(con);

            if ("listar".equals(action)) {
                List<Pedido> pedidos = dao.getAll();
                request.setAttribute("pedidos", pedidos);
                request.getRequestDispatcher("listaPedido.jsp").forward(request, response);

            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
                response.sendRedirect("PedidoServlet?action=listar");

            } else if ("cadastro".equals(action)) {

                ProdutoDao produtoDao = new ProdutoDao(con); // Assumindo que ProdutoDao tem construtor com Connection
                List<Produto> produtos = produtoDao.getAll(); // lista de produtos para selecionar
                request.setAttribute("produtos", produtos);

                request.getRequestDispatcher("cadastroPedido.jsp").forward(request, response);

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Pedido pedido = dao.getById(id);
                request.setAttribute("pedido", pedido);
                request.getRequestDispatcher("editarPedido.jsp").forward(request, response);

            } else {
                response.getWriter().println("Ação inválida para GET");
            }

        } catch (DaoException |

                SQLException e) {
            throw new ServletException("Erro no processamento", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        try (Connection con = getConnection()) {
            PedidoDao dao = new PedidoDao(con);

            if ("inserir".equals(action)) {
                Pedido pedido = new Pedido();

                String dataStr = request.getParameter("data");
                java.sql.Date data = java.sql.Date.valueOf(dataStr);
                pedido.setData(data);

                pedido.setStatus(request.getParameter("status"));
                pedido.setValorTotal(Double.valueOf(request.getParameter("valorTotal")));

                String[] produtoIds = request.getParameterValues("produtoIds");
                if (produtoIds == null || produtoIds.length == 0) {
                    throw new ServletException("Nenhum produto selecionado!");
                }

                List<Produto> produtos = new ArrayList<>();
                for (String pid : produtoIds) {
                    Produto p = new Produto();
                    p.setId(Integer.parseInt(pid));
                    produtos.add(p);
                }
                pedido.setProdutos(produtos);

                dao.insert(pedido);
                response.sendRedirect("PedidoServlet?action=listar");

            } else if ("atualizar".equals(action)) {
                Pedido pedido = new Pedido();
                int id = Integer.parseInt(request.getParameter("id"));
                pedido.setId(id);
                String dataStr = request.getParameter("data");
                java.sql.Date data = java.sql.Date.valueOf(dataStr);
                pedido.setData(data);

                pedido.setStatus(request.getParameter("status"));

                pedido.setValorTotal(Double.valueOf(request.getParameter("valorTotal")));

                dao.alterar(pedido);
                response.sendRedirect("PedidoServlet?action=listar");

            } else {
                response.getWriter().println("Ação inválida para POST");
            }
        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro ao operar com pedido", e);
        }
    }

    /*
     * protected void doPost(HttpServletRequest request, HttpServletResponse
     * response) throws IOException {
     * response.setContentType("text/html");
     * response.setCharacterEncoding("UTF-8");
     * response.getWriter().println("<html><body>");
     * response.getWriter().println("Id: " + request.getParameter("Id") + "<br>");
     * response.getWriter().println("Data: " + request.getParameter("data") +
     * "<br>");
     * response.getWriter().println("Status: " + request.getParameter("status") +
     * "<br>");
     * response.getWriter().println("Valor Total: " +
     * request.getParameter("valorTotal") + "<br>");
     * response.getWriter().println("</body></html>");
     * }
     * 
     * protected void doGet(HttpServletRequest request, HttpServletResponse
     * response)
     * throws IOException, ServletException {
     * 
     * List<Pedido> pedidos = criaPedidos();
     * 
     * request.setAttribute("pedidos", pedidos);
     * 
     * RequestDispatcher rd = request.getRequestDispatcher("listaPedido.jsp");
     * 
     * rd.forward(request, response);
     * }
     * 
     * private List<Pedido> criaPedidos() {
     * List<Pedido> pedidos = new ArrayList<>();
     * List<Produto> produtosVazio = new ArrayList<>();
     * Pedido pedido1 = new Pedido(1, new Date(), "PENDENTE", produtosVazio);
     * Pedido pedido2 = new Pedido(2, new Date(), "PENDENTE", produtosVazio);
     * Pedido pedido3 = new Pedido(3, new Date(), "PENDENTE", produtosVazio);
     * Pedido pedido4 = new Pedido(4, new Date(), "PENDENTE", produtosVazio);
     * 
     * pedidos.add(pedido1);
     * pedidos.add(pedido2);
     * pedidos.add(pedido3);
     * pedidos.add(pedido4);
     * return pedidos;
     * }
     */
}
