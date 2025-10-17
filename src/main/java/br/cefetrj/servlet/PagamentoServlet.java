package br.cefetrj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.PagamentoDao;
import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Pagamento;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/PagamentoServlet")
public class PagamentoServlet extends HttpServlet {
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
            PagamentoDao dao = new PagamentoDao(con);

            if ("listar".equals(action)) {
                List<Pagamento> pagamentos = dao.getAll();
                request.setAttribute("pagamentos", pagamentos);
                request.getRequestDispatcher("listaPagamento.jsp").forward(request, response);

            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
                response.sendRedirect("PagamentoServlet?action=listar");

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Pagamento pagamento = dao.getById(id);
                request.setAttribute("pagamento", pagamento);
                request.getRequestDispatcher("editarPagamento.jsp").forward(request, response);

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
            PagamentoDao dao = new PagamentoDao(con);

            if ("inserir".equals(action)) {
                Pagamento pagamento = new Pagamento();
                pagamento.setValor(Double.valueOf(request.getParameter("valor")));

                String dataStr = request.getParameter("data");
                java.sql.Date data = java.sql.Date.valueOf(dataStr);
                pagamento.setData(data);

                pagamento.setForma(request.getParameter("forma"));

                dao.insert(pagamento); // insere no banco
                List<Pagamento> pagamentos = dao.getAll(); // busca lista atualizada
                request.setAttribute("pagamentos", pagamentos); // coloca no request
                request.getRequestDispatcher("listaPagamento.jsp").forward(request, response);

            } else if ("atualizar".equals(action)) {
                Pagamento pagamento = new Pagamento();
                int id = Integer.parseInt(request.getParameter("id"));
                pagamento.setIdPagamento(id);
                pagamento.setValor(Double.valueOf(request.getParameter("valor")));

                String dataStr = request.getParameter("data");
                java.sql.Date data = java.sql.Date.valueOf(dataStr);
                pagamento.setData(data);

                pagamento.setForma(request.getParameter("forma"));

                dao.alterar(pagamento);
                response.sendRedirect("PagamentoServlet?action=listar");

            } else {
                response.getWriter().println("Ação inválida para POST");
            }
        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro ao operar com pagamento", e);
        }
    }
    /*
     * private static final long serialVersionUID = 3L;
     * 
     * protected void doPost(final HttpServletRequest request, final
     * HttpServletResponse response) throws IOException {
     * response.setContentType("text/html");
     * response.setCharacterEncoding("UTF-8");
     * response.getWriter().println("<html><body>");
     * response.getWriter().println("Id: " + request.getParameter("Id") + "<br>");
     * response.getWriter().println("Valor: " + request.getParameter("valor") +
     * "<br>");
     * response.getWriter().println("Data: " + request.getParameter("data") +
     * "<br>");
     * response.getWriter().println("Forma: " + request.getParameter("forma") +
     * "<br>");
     * response.getWriter().println("Status: " + request.getParameter("status") +
     * "<br>");
     * 
     * response.getWriter().println("</body></html>");
     * }
     * 
     * protected void doGet(final HttpServletRequest request, final
     * HttpServletResponse response)
     * throws IOException, ServletException {
     * 
     * final List<Pagamento> pagamentos = criaPagamento();
     * 
     * request.setAttribute("pagamentos", pagamentos);
     * 
     * final RequestDispatcher rd =
     * request.getRequestDispatcher("listaPagamento.jsp");
     * 
     * rd.forward(request, response);
     * }
     * 
     * private List<Pagamento> criaPagamento() {
     * List<Pagamento> pagamentos = new ArrayList<>();
     * Pagamento pagamento1 = new Pagamento(1, 250.00, new Date(), "pix");
     * Pagamento pagamento2 = new Pagamento(2, 500.00, new Date(),
     * "cartão de crédito");
     * Pagamento pagamento3 = new Pagamento(3, 150.00, new Date(), "dinheiro");
     * Pagamento pagamento4 = new Pagamento(4, 55.00, new Date(), "pix");
     * Pagamento pagamento5 = new Pagamento(5, 100.00, new Date(), "dinheiro");
     * 
     * pagamentos.add(pagamento1);
     * pagamentos.add(pagamento2);
     * pagamentos.add(pagamento3);
     * pagamentos.add(pagamento4);
     * pagamentos.add(pagamento5);
     * return pagamentos;
     * }
     */
}
