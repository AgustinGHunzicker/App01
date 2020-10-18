package com.example.myapplication.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
public class Tarjeta implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String numero;
    private String ccv;
    private LocalDate vencimiento;
    private Boolean esCredito;

    public Tarjeta () {}

    public Tarjeta(String numero, String ccv, LocalDate vencimiento, boolean esCredito) {
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