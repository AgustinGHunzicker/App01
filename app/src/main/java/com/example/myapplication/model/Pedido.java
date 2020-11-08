package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity//(foreignKeys = @ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "idUsuario", onDelete = CASCADE))
public class Pedido implements Serializable {
    /* ---- RELACIONES -----*/
    @Ignore
    @ColumnInfo(name="idUsuario")
    private Integer idUsuario;
    //TODO esto es una lista many to many
    @Ignore//@ColumnInfo(name="idPlato")
    private Integer idPlato;

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private Integer id;
    //TODO esto lo pide en la app, pero ya lo da el usuario ¿?
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="seEnvia")
    private Boolean seEnvia;
    @ColumnInfo(name="direccion")
    private String direccion;
    @ColumnInfo(name="price")
    private Double price;
    @ColumnInfo(name="cantidadPlatos")
    private Integer cantidadPlatos;

    public Pedido() { }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSeEnvia() {
        return seEnvia;
    }

    public void setSeEnvia(Boolean seEnvia) {
        this.seEnvia = seEnvia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCantidadPlatos() {
        return cantidadPlatos;
    }

    public void setCantidadPlatos(Integer cantidadPlatos) {
        this.cantidadPlatos = cantidadPlatos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idUsuario=" + idUsuario +
                ", idPlato=" + idPlato +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", seEnvia=" + seEnvia +
                ", direccion='" + direccion + '\'' +
                ", price=" + price +
                ", cantidadPlatos=" + cantidadPlatos +
                '}';
    }
}
