package com.example.myapplication.model;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Tarjeta implements Serializable {

    private Integer id;
    private String numero;
    private String ccv;
    private LocalDate vencimiento;
    private Boolean esCredito;

    public Tarjeta () {}

    public Tarjeta(Integer id, String numero, String ccv, LocalDate vencimiento, boolean esCredito) {
        this.id = id;
        this.numero = numero;
        this.ccv = ccv;
        this.vencimiento = vencimiento;
        this.esCredito = esCredito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public boolean isEsCredito() {
        return esCredito;
    }

    public void setEsCredito(Boolean esCredito) {
        this.esCredito = esCredito;
    }
}