package br.cefetrj.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto extends Entidade {
    private String nome;
    private String tamanho;
    private String cor;
    private double preco;
    @ManyToMany
    @JoinTable(name = "pedido_produto", // nome da tabela intermediária
            joinColumns = @JoinColumn(name = "produto_id"), // FK para Aluno
            inverseJoinColumns = @JoinColumn(name = "pedido_id") // FK para Curso
    )
    private List<Pedido> pedidos;

    public Produto() {

    }

    public Produto(String nome, String tamanho, String cor, double preco) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.cor = cor;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
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

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
