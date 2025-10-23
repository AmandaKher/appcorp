package br.cefetrj.dao;

import br.cefetrj.model.Produto;

public class ProdutoDao extends GenericDao<Produto> {

    public ProdutoDao(Class<Produto> clazz) {
        super(clazz);
    }
}