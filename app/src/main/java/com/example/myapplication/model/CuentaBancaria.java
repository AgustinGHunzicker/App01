package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "idUsuario",
        onDelete = CASCADE))
public class CuentaBancaria implements Serializable {

    /* ---- RELACIONES -----*/
    @ColumnInfo(name="idUsuario")
    private Integer idUsuario;

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private Integer id;
    @ColumnInfo(name="cbu")
    private String cbu;
    @ColumnInfo(name="alias")
    private String alias;

    public CuentaBancaria () {}

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
