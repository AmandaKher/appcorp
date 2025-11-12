package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Produto;

public class ProdutoToInput implements Serializable {
    private Integer id;
    private String nome;
    private String tamanho;
    private String cor;
    private double preco;

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

    public Produto build() {
        Produto produto = new Produto();
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setTamanho(this.tamanho);
        produto.setCor(this.cor);
        produto.setPreco(Double.valueOf(this.preco));

        return produto;
    }

}
