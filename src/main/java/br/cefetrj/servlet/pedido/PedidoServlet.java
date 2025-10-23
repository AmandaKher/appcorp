package br.cefetrj.servlet.pedido;

import br.cefetrj.model.Pedido;
import br.cefetrj.servlet.GenericServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Para acessar o formulário de cadastro:
 * http://localhost:8080/projeto/pedido?acao=novo
 * Para listar todos os pedido:
 * http://localhost:8080/projeto/pedido?acao=listar
 * Para editar um pedido (supondo que o ID seja 1):
 * http://localhost:8080/projetos/pedido?acao=buscar&id=1
 */
@WebServlet("/pedido")
public class PedidoServlet extends GenericServlet<Pedido> {

    @Override
    protected Pedido preencherEntidade(HttpServletRequest request) {
        Pedido pedido = new Pedido();
        String id = request.getParameter("id");
        pedido.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null);

        String dataStr = request.getParameter("data");
        java.sql.Date data = java.sql.Date.valueOf(dataStr);
        pedido.setData(data);

        pedido.setStatus(request.getParameter("status"));
        pedido.setValorTotal(Double.valueOf(request.getParameter("valorTotal")));

        return pedido;
    }
}
