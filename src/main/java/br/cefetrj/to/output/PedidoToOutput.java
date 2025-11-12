package br.cefetrj.to.output;

import java.util.List;

import br.cefetrj.model.Pedido;
import br.cefetrj.utils.DateUtils;

public class PedidoToOutput {
    private Integer id;
    private String dataPedido;
    private List<ProdutoToOutput> produtos;

    public PedidoToOutput(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = DateUtils.format(pedido.getData());
        if (pedido.getProdutos() != null) {
            this.produtos = pedido.getProdutos().stream().map(produto -> {
                ProdutoToOutput pedidoTO = new ProdutoToOutput(produto);
                return pedidoTO;
            }).toList();

        }
    }

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

    public List<ProdutoToOutput> getProduto() {
        return produtos;
    }

    public void setProduto(List<ProdutoToOutput> produtos) {
        this.produtos = produtos;
    }
}
