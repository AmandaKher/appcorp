package br.cefetrj.to.output;

import br.cefetrj.model.Vendedor;
import br.cefetrj.utils.DateUtils;

public class VendedorToOutput {
    private Integer id;
    private String nome;
    private String dataNascimento;
    private Long cpf;

    public VendedorToOutput(Vendedor vendedor) {
        this.id = vendedor.getId();
        this.nome = vendedor.getNome();
        this.dataNascimento = DateUtils.format(vendedor.getDataNascimento());
        this.cpf = vendedor.getCpf();
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
