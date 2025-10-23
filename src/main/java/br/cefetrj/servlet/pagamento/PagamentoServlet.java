package br.cefetrj.servlet.pagamento;

import br.cefetrj.model.Pagamento;
import br.cefetrj.servlet.GenericServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Para acessar o formulário de cadastro:
 * http://localhost:8080/projeto/pagamento?acao=novo
 * Para listar todos os pagamento:
 * http://localhost:8080/projeto/pagamento?acao=listar
 * Para editar um pagamento (supondo que o ID seja 1):
 * http://localhost:8080/projetos/pagamento?acao=buscar&id=1
 */
@WebServlet("/pagamento")
public class PagamentoServlet extends GenericServlet<Pagamento> {

    @Override
    protected Pagamento preencherEntidade(HttpServletRequest request) {
        Pagamento pagamento = new Pagamento();
        String id = request.getParameter("id");
        pagamento.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null);
        pagamento.setValor(Double.valueOf(request.getParameter("valor")));

        String dataStr = request.getParameter("dataPagamento");
        java.sql.Date dataPagamento = java.sql.Date.valueOf(dataStr);
        pagamento.setDataPagamento(dataPagamento);

        pagamento.setForma(request.getParameter("forma"));

        return pagamento;
    }
}
