package br.cefetrj.to.output;

import java.util.List;

import br.cefetrj.model.Produto;

public class ProdutoToOutput {
    private Integer id;
    private String nome;
    private String tamanho;
    private String cor;
    private double preco;
    private List<PedidoToOutput> pedidos;

    public ProdutoToOutput(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.tamanho = produto.getTamanho();
        this.cor = produto.getCor();
        this.preco = produto.getPreco();
    }

    public ProdutoToOutput(Produto produto, boolean carregaPedido) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.tamanho = produto.getTamanho();
        this.cor = produto.getCor();
        this.preco = produto.getPreco();
        if (carregaPedido && produto.getPedidos() != null) {
            this.pedidos = produto.getPedidos().stream().map(pedido -> {
                PedidoToOutput pedidoTO = new PedidoToOutput(pedido);
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<PedidoToOutput> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoToOutput> pedidos) {
        this.pedidos = pedidos;
    }
}
