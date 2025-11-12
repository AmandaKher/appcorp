package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Vendedor;
import br.cefetrj.utils.DateUtils;

public class VendedorToInput implements Serializable {
    private Integer id;
    private String nome;
    private String dataNascimento;
    private Long cpf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Vendedor build() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(this.id);
        vendedor.setNome(this.nome);
        vendedor.setCpf(this.cpf);
        vendedor.setDataNascimento(DateUtils.parse(this.dataNascimento));

        return vendedor;
    }
}
