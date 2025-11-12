package br.cefetrj.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "pedido")
public class Pedido extends Entidade {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String status;
    @ManyToMany
    @JoinTable(name = "pedido_produto", // nome da tabela intermediária
            joinColumns = @JoinColumn(name = "pedido_id"), // FK para Aluno
            inverseJoinColumns = @JoinColumn(name = "produto_id") // FK para Curso
    )
    private List<Produto> produtos;
    private double valorTotal;

    public Pedido() {

    }

    public Pedido(LocalDate data, String status, List<Produto> produtos) {
        this.data = data;
        this.status = status;
        this.produtos = produtos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}