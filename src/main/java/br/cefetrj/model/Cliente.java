package br.cefetrj.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Cliente extends Pessoa {
    @ManyToMany
    @JoinTable(name = "cliente_pedido", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "pedido_id"))
    private List<Pedido> listaPedidos = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(Pessoa pessoa) {
        super(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getCpf());
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

}
