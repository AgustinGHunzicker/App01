package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "idUsuario", onDelete = CASCADE))
public class Pedido implements Serializable {
    /* ---- RELACIONES -----*/
    @ColumnInfo(name="idUsuario")
    private Integer idUsuario;

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
    //TODO ver si agrega la hora tambien, si no lo hace agregar un atributo con esa info
    @ColumnInfo(name="momentoPedido")
    private Date fechaPedido;

    public Pedido() { }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idUsuario=" + idUsuario +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", seEnvia=" + seEnvia +
                ", direccion='" + direccion + '\'' +
                ", price=" + price +
                ", cantidadPlatos=" + cantidadPlatos +
                ", fechaPedido=" + fechaPedido +
                '}';
    }
}
