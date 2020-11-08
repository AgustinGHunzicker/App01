package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable {

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="clave")
    private String clave;
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="credito")
    private Double credito;

    public Usuario() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }
}
