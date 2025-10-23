package br.cefetrj.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento extends Entidade {
    private double valor;
    private Date dataPagamneto;
    private String forma;

    public Pagamento() {

    }

    public Pagamento(double valor, Date dataPagamento, String forma) {
        this.valor = valor;
        this.dataPagamneto = dataPagamento;
        this.forma = forma;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamneto;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamneto = dataPagamento;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
