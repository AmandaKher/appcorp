package br.cefetrj.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa {
    private String email;
    private String senha;
    private boolean ativo;
    private String papel; // e.g., "ADMIN", "USER"

    public Usuario() {

    }

    public Usuario(String nome, Date dataNascimento, Long cpf,
            String email, String senha, String papel) {
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
