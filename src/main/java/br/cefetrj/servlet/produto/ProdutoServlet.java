package br.cefetrj.servlet.produto;

import br.cefetrj.model.Produto;
import br.cefetrj.servlet.GenericServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Para acessar o formulário de cadastro:
 * http://localhost:8080/projeto/produto?acao=novo
 * Para listar todos os produto:
 * http://localhost:8080/projeto/produto?acao=listar
 * Para editar um produto (supondo que o ID seja 1):
 * http://localhost:8080/projetos/produto?acao=buscar&id=1
 */
@WebServlet("/produto")
public class ProdutoServlet extends GenericServlet<Produto> {

    @Override
    protected Produto preencherEntidade(HttpServletRequest request) {
        Produto produto = new Produto();
        String id = request.getParameter("id");
        produto.setId(id != null && !id.isEmpty() ? Integer.parseInt(id) : null);
        produto.setNome(request.getParameter("nome"));
        produto.setCor(request.getParameter("cor"));
        produto.setTamanho(request.getParameter("tamanho"));
        produto.setPreco(Double.valueOf(request.getParameter("preco")));

        return produto;
    }
}
