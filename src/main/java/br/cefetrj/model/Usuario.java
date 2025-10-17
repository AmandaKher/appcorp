package br.cefetrj.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;
    private String email;
    private String senha;
    private boolean ativo;
    private String papel; // e.g., "ADMIN", "USER"

    public Usuario() {

    }

    public Usuario(Integer idPessoa, String nome, LocalDate dataNascimento, Long cpf,
            Integer idUsuario, String email, String senha, String papel) {
        super(idPessoa, nome, dataNascimento, cpf);
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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
