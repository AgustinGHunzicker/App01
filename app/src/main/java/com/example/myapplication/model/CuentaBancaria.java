package com.example.myapplication.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CuentaBancaria implements Serializable {

    private Integer id;
    private String cbu;
    private String alias;

    public CuentaBancaria () {}

    public CuentaBancaria(Integer id, String cbu, String alias) {
        this.id = id;
        this.cbu = cbu;
        this.alias = alias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
