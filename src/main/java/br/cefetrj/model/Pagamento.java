package br.cefetrj.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento extends Entidade {
    private double valor;
    private LocalDate dataPagamneto;
    private String forma;

    public Pagamento() {

    }

    public Pagamento(double valor, LocalDate dataPagamento, String forma) {
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

    public LocalDate getDataPagamento() {
        return dataPagamneto;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamneto = dataPagamento;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
