package br.cefetrj.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "pedido")
public class Pedido extends Entidade {
    private Date data;
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

    public Pedido(Date data, String status, List<Produto> produtos) {
        this.data = data;
        this.status = status;
        this.produtos = produtos;
        this.valorTotal = calcularTotal();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

    public double calcularTotal() {
        valorTotal = 0;
        for (Produto p : produtos) {
            valorTotal += p.getPreco();
        }
        return valorTotal;
    }
}