package app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import app.database.Converters;

@Entity
public class Pedido implements Serializable {

    @Ignore
    private ArrayList<Long> platosPedidos;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idPedido")
    private long idPedido;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="seEnvia")
    private boolean seEnvia;

    @ColumnInfo(name="direccion")
    private String direccion;

    @ColumnInfo(name="precio")
    private double precio;

    @ColumnInfo(name="cantidadPlatos")
    private int cantidadPlatos;

    @TypeConverters(Converters.class)
    @ColumnInfo(name="fechaPedido")
    private LocalDate fechaPedido;

    public Pedido() { }

    public ArrayList<Long> getPlatosPedidos() {
        if(platosPedidos == null) platosPedidos = new ArrayList<>();
        return platosPedidos;
    }

    public void setPlatosPedidos(ArrayList<Long> platosPedidos) {
        this.platosPedidos = platosPedidos;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
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

    public void setSeEnvia(boolean seEnvia) {
        this.seEnvia = seEnvia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getCantidadPlatos() {
        return cantidadPlatos;
    }

    public void setCantidadPlatos(int cantidadPlatos) {
        this.cantidadPlatos = cantidadPlatos;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

}
