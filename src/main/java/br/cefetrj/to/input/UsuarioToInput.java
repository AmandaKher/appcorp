package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Usuario;

public class UsuarioToInput implements Serializable {
    private String email;
    private String senha;
    private boolean ativo;
    private String papel;

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

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setAtivo(this.ativo);
        usuario.setPapel(this.papel);

        return usuario;
    }
}
