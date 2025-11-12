package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Cliente;
import br.cefetrj.utils.DateUtils;

public class ClienteToInput implements Serializable {
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

    public Cliente build() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setDataNascimento(DateUtils.parse(this.dataNascimento));

        return cliente;
    }
}
