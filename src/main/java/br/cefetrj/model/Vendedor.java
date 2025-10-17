package br.cefetrj.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "vendedor")
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private double limiteDesconto;
    @Transient
    private List<Pedido> listaPedidos = new ArrayList<>();

    public Vendedor() {

    }

    public Vendedor(int id, String nome, String email, String telefone, double limiteDesconto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.limiteDesconto = limiteDesconto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getLimiteDesconto() {
        return limiteDesconto;
    }

    public void setLimiteDesconto(double limiteDesconto) {
        this.limiteDesconto = limiteDesconto;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Pedido fazerPedido(List<Produto> produtos) {
        Pedido pedido = new Pedido(listaPedidos.size() + 1, new Date(), "Pendente", produtos);
        listaPedidos.add(pedido);
        System.out.println("Pedido realizado com sucesso!");
        return pedido;
    }

    public List<Produto> consultarEstoque(List<Produto> estoque) {
        System.out.println("Consultando estoque...");
        return estoque;
    }
}
