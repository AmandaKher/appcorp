package br.cefetrj.servlet.vendedor;

import br.cefetrj.model.Vendedor;
import br.cefetrj.servlet.GenericServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Para acessar o formulário de cadastro:
 * http://localhost:8080/projeto/vendedor?acao=novo
 * Para listar todos os vendedor:
 * http://localhost:8080/projeto/vendedor?acao=listar
 * Para editar um vendedor (supondo que o ID seja 1):
 * http://localhost:8080/projetos/vendedor?acao=buscar&id=1
 */
@WebServlet("/vendedor")
public class VendedorServlet extends GenericServlet<Vendedor> {

    @Override
    protected Vendedor preencherEntidade(HttpServletRequest request) {
        Vendedor vendedor = new Vendedor();
        String id = request.getParameter("id");
        vendedor.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null);
        vendedor.setNome(request.getParameter("nome"));

        vendedor.setCpf(Long.parseLong(request.getParameter("cpf")));
        String dataStr = request.getParameter("dataNascimento");
        java.sql.Date data = java.sql.Date.valueOf(dataStr);
        vendedor.setDataNascimento(data);

        vendedor.setEmail(request.getParameter("email"));
        vendedor.setSenha(request.getParameter("senha"));
        vendedor.setPapel(request.getParameter("papel"));

        return vendedor;
    }
}
