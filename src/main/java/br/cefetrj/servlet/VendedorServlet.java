package br.cefetrj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.VendedorDao;
import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Vendedor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/VendedorServlet")
public class VendedorServlet extends HttpServlet {

    private Connection getConnection() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projeto",
                    "root",
                    "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Erro ao conectar no banco", e);
        }
    }

    // ----------- GET → LISTAR VENDEDORES -----------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try (Connection con = getConnection()) {
            VendedorDao dao = new VendedorDao(con);

            if ("listar".equals(action)) {
                List<Vendedor> vendedores = dao.getAll();
                request.setAttribute("vendedores", vendedores);
                request.getRequestDispatcher("listaVendedor.jsp").forward(request, response);

            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
                response.sendRedirect("VendedorServlet?action=listar");

            } else if ("cadastro".equals(action)) {

                request.getRequestDispatcher("cadastroVendedor.jsp").forward(request, response);

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vendedor vendedor = dao.getById(id);
                request.setAttribute("vendedor", vendedor);
                request.getRequestDispatcher("editarVendedor.jsp").forward(request, response);

            } else {
                response.getWriter().println("Ação inválida para GET");
            }

        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro no processamento", e);
        }
    }

    // ----------- POST → INSERIR VENDEDORES -----------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        try (Connection con = getConnection()) {
            VendedorDao dao = new VendedorDao(con);

            if ("inserir".equals(action)) {
                Vendedor vendedor = new Vendedor();
                vendedor.setNome(request.getParameter("nome"));
                vendedor.setEmail(request.getParameter("email"));
                vendedor.setTelefone(request.getParameter("telefone"));
                vendedor.setLimiteDesconto(Double.parseDouble(request.getParameter("limiteDesconto")));
                dao.insert(vendedor); // insere no banco
                List<Vendedor> vendedores = dao.getAll(); // busca lista atualizada
                request.setAttribute("vendedores", vendedores); // coloca no request
                request.getRequestDispatcher("listaVendedor.jsp").forward(request, response);

            } else if ("atualizar".equals(action)) {
                Vendedor vendedor = new Vendedor();
                int id = Integer.parseInt(request.getParameter("id"));
                vendedor.setId(id);
                vendedor.setNome(request.getParameter("nome"));
                vendedor.setEmail(request.getParameter("email"));
                vendedor.setTelefone(request.getParameter("telefone"));
                vendedor.setLimiteDesconto(Double.parseDouble(request.getParameter("limiteDesconto")));
                dao.alterar(vendedor);
                response.sendRedirect("VendedorServlet?action=listar");

            } else {
                response.getWriter().println("Ação inválida para POST");
            }
        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro ao operar com vendedor", e);
        }
    }

    /*
     * @Override
     * protected void doGet(HttpServletRequest request, HttpServletResponse
     * response)
     * throws IOException, ServletException {
     * 
     * List<Vendedor> vendedores = criaVendedor();
     * 
     * request.setAttribute("vendedores", vendedores);
     * 
     * RequestDispatcher rd = request.getRequestDispatcher("listaVendedor.jsp");
     * rd.forward(request, response);
     * }
     * 
     * 
     * @Override
     * protected void doPost(HttpServletRequest request, HttpServletResponse
     * response)
     * throws IOException, ServletException {
     * 
     * String nome = request.getParameter("nome");
     * String email = request.getParameter("email");
     * String telefone = request.getParameter("telefone");
     * double limiteDesconto =
     * Double.parseDouble(request.getParameter("limiteDesconto"));
     * 
     * Vendedor novoVendedor = new Vendedor(1, nome, email, telefone,
     * limiteDesconto);
     * 
     * List<Vendedor> vendedores = new ArrayList<>();
     * vendedores.add(novoVendedor);
     * 
     * request.setAttribute("vendedores", vendedores);
     * RequestDispatcher rd = request.getRequestDispatcher("listaVendedor.jsp");
     * rd.forward(request, response);
     * }
     * 
     * private List<Vendedor> criaVendedor() {
     * List<Vendedor> vendedores = new ArrayList<>();
     * Vendedor vendedor1 = new Vendedor(1, "Joao", "joao@gmail.com.br",
     * "22992784265", 200);
     * vendedores.add(vendedor1);
     * return vendedores;
     * }
     */
}
