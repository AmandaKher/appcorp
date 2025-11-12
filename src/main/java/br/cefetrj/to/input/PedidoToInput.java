package br.cefetrj.to.input;

import java.io.Serializable;
import java.util.List;

import br.cefetrj.model.Pedido;
import br.cefetrj.utils.DateUtils;

public class PedidoToInput implements Serializable {
    private Integer id;
    private String dataPedido;
    private List<ProdutoToInput> produtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ProdutoToInput> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoToInput> produtos) {
        this.produtos = produtos;
    }

    public Pedido build() {
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setData(DateUtils.parse(this.dataPedido));
        pedido.setProdutos(produtos.stream().map(produtoTO -> {
            return produtoTO.build();
        }).toList());

        return pedido;
    }
}
