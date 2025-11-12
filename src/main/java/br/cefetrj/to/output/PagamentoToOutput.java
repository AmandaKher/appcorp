package br.cefetrj.to.output;

import br.cefetrj.model.Pagamento;
import br.cefetrj.utils.DateUtils;

public class PagamentoToOutput {
    private Integer id;
    private double valor;
    private String dataPagamneto;

    public PagamentoToOutput(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.valor = pagamento.getValor();
        this.dataPagamneto = DateUtils.format(pagamento.getDataPagamento());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataPagamneto() {
        return dataPagamneto;
    }

    public void setDataPagamneto(String dataPagamneto) {
        this.dataPagamneto = dataPagamneto;
    }

}
