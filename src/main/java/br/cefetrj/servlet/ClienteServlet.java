
package br.cefetrj.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.ClienteDao;
import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

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
            ClienteDao dao = new ClienteDao(con);

            if ("listar".equals(action)) {
                List<Cliente> clientes = dao.getAll();
                request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("listaCliente.jsp").forward(request, response);

            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluir(id);
                response.sendRedirect("ClienteServlet?action=listar");

            } else if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Cliente cliente = dao.getById(id);
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);

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
            ClienteDao dao = new ClienteDao(con);

            if ("inserir".equals(action)) {
                Cliente cliente = new Cliente();
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setTelefone(request.getParameter("telefone"));

                dao.insert(cliente); // insere no banco
                List<Cliente> clientes = dao.getAll(); // busca lista atualizada
                request.setAttribute("clientes", clientes); // coloca no request
                request.getRequestDispatcher("listaCliente.jsp").forward(request, response);

            } else if ("atualizar".equals(action)) {
                Cliente cliente = new Cliente();
                int id = Integer.parseInt(request.getParameter("id"));
                cliente.setId(id);
                cliente.setNome(request.getParameter("nome"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setTelefone(request.getParameter("telefone"));

                dao.alterar(cliente);
                response.sendRedirect("ClienteServlet?action=listar");

            } else {
                response.getWriter().println("Ação inválida para POST");
            }
        } catch (DaoException | SQLException e) {
            throw new ServletException("Erro ao operar com cliente", e);
        }
    }
    /*
     * @Override
     * protected void doPost(HttpServletRequest request, HttpServletResponse
     * response)
     * throws IOException, ServletException {
     * 
     * String nome = request.getParameter("nome");
     * String cpf = request.getParameter("cpf");
     * String telefone = request.getParameter("telefone");
     * 
     * Cliente novoCliente = new Cliente(1, nome, cpf, telefone);
     * 
     * List<Cliente> clientes = new ArrayList<>();
     * clientes.add(novoCliente);
     * 
     * request.setAttribute("clientes", clientes);
     * RequestDispatcher rd = request.getRequestDispatcher("listaCliente.jsp");
     * rd.forward(request, response);
     * }
     * 
     * @Override
     * protected void doGet(HttpServletRequest request, HttpServletResponse
     * response)
     * throws IOException, ServletException {
     * 
     * List<Cliente> clientes = criaClientes();
     * 
     * request.setAttribute("clientes", clientes);
     * 
     * RequestDispatcher rd = request.getRequestDispatcher("listaCliente.jsp");
     * rd.forward(request, response);
     * }
     * 
     * private List<Cliente> criaClientes() {
     * List<Cliente> clientes = new ArrayList<>();
     * Cliente cliente1 = new Cliente(1, "Amanda", "56678492483", "22992487516");
     * clientes.add(cliente1);
     * return clientes;
     * }
     */
}
