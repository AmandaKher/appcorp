package br.cefetrj.model;

import java.util.Date;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Pessoa extends Entidade {
    private String nome;
    private Date dataNascimento;
    private Long cpf;

    public Pessoa() {

    }

    public Pessoa(String nome, Date dataNascimento, long cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

}