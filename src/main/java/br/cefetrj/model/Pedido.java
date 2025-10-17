package br.cefetrj.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPedido;
    private Date data;
    private String status;
    @Transient
    private List<Produto> produtos;
    private double valorTotal;

    public Pedido() {

    }

    public Pedido(int idPedido, Date data, String status, List<Produto> produtos) {
        this.idPedido = idPedido;
        this.data = data;
        this.status = status;
        this.produtos = produtos;
        this.valorTotal = calcularTotal();
    }

    public int getId() {
        return idPedido;
    }

    public void setId(int idPedido) {
        this.idPedido = idPedido;
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