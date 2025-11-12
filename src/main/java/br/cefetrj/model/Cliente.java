package br.cefetrj.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Cliente extends Pessoa {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
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
