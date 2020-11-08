package com.example.myapplication.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "idUsuario",
        onDelete = CASCADE))
public class Tarjeta implements Serializable {
    /* ---- RELACIONES -----*/
    @ColumnInfo(name="idUsuario")
    private Integer idUsuario;

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo(name="numero")
    private String numero;
    @ColumnInfo(name="ccv")
    private String ccv;
    @ColumnInfo(name="vencimiento")
    private Date vencimiento;
    @ColumnInfo(name="esCredito")
    private Boolean esCredito;

    public Tarjeta () {}

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
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

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Boolean getEsCredito() {
        return esCredito;
    }

    public void setEsCredito(Boolean esCredito) {
        this.esCredito = esCredito;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}