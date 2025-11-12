package br.cefetrj.to.output;

import br.cefetrj.model.Cliente;
import br.cefetrj.utils.DateUtils;

public class ClienteToOutput {
    private Integer id;
    private String nome;
    private String dataNascimento;
    private Long cpf;

    public ClienteToOutput(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.dataNascimento = DateUtils.format(cliente.getDataNascimento());
        this.cpf = cliente.getCpf();
    }

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
}
