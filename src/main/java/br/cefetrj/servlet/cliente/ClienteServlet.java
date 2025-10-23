package br.cefetrj.servlet.cliente;

import br.cefetrj.model.Cliente;
import br.cefetrj.servlet.GenericServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Para acessar o formulário de cadastro:
 * http://localhost:8080/projeto/cliente?acao=novo
 * Para listar todos os clientes:
 * http://localhost:8080/projeto/cliente?acao=listar
 * Para editar um cliente (supondo que o ID seja 1):
 * http://localhost:8080/projetos/cliente?acao=buscar&id=1
 */
@WebServlet("/cliente")
public class ClienteServlet extends GenericServlet<Cliente> {

    @Override
    protected Cliente preencherEntidade(HttpServletRequest request) {
        Cliente cliente = new Cliente();
        String id = request.getParameter("id");
        cliente.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null);
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(Long.parseLong(request.getParameter("cpf")));
        String dataStr = request.getParameter("dataNascimento");
        java.sql.Date data = java.sql.Date.valueOf(dataStr);
        cliente.setDataNascimento(data);

        return cliente;
    }
}
