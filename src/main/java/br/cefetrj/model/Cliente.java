package br.cefetrj.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCliente;
    private String telefone;

    public Cliente() {
        super();
    }

    public Cliente(Pessoa pessoa, Integer idCliente, String telefone) {
        super(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getCpf());
        this.idCliente = idCliente;
        this.telefone = telefone;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
