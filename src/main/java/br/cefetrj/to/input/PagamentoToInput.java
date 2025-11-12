package br.cefetrj.to.input;

import java.io.Serializable;

import br.cefetrj.model.Pagamento;
import br.cefetrj.utils.DateUtils;

public class PagamentoToInput implements Serializable {
    private Integer id;
    private double valor;
    private String dataPagamneto;

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

    public Pagamento build() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(this.id);
        pagamento.setValor(Double.valueOf(this.valor));
        pagamento.setDataPagamento(DateUtils.parse(this.dataPagamneto));

        return pagamento;
    }

}
