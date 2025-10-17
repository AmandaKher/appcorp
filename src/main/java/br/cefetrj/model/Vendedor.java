package br.cefetrj.model;

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
public class Vendedor extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idVendedor;
    private double limiteDesconto;
    @Transient
    private List<Pedido> listaPedidos = new ArrayList<>();

    public Vendedor() {

    }

    public Vendedor(Usuario usuario, Integer idVendedor, String telefone, double limiteDesconto) {
        super(usuario.getIdPessoa(), usuario.getNome(), usuario.getDataNascimento(), usuario.getCpf(),
                usuario.getIdUsuario(), usuario.getEmail(), usuario.getSenha(), usuario.getPapel());

        this.idVendedor = idVendedor;
        this.limiteDesconto = limiteDesconto;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
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

}
