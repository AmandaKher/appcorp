package br.cefetrj.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Vendedor extends Usuario {
    @ManyToMany
    @JoinTable(name = "vendedor_pedido", // nome da tabela intermediária
            joinColumns = @JoinColumn(name = "vendedor_id"), // FK para Aluno
            inverseJoinColumns = @JoinColumn(name = "pedido_id")) // FK para Curso
    private List<Pedido> listaPedidos = new ArrayList<>();

    public Vendedor() {

    }

    public Vendedor(Usuario usuario) {
        super(usuario.getNome(), usuario.getDataNascimento(), usuario.getCpf(),
                usuario.getEmail(), usuario.getSenha(), usuario.getPapel());
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

}
