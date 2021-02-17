package app.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.time.LocalDate;

import app.database.Converters;

@Entity
public class Pedido implements Serializable {

    @ColumnInfo(name="idUsuario")
    private long idUsuario;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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


    @Override
    public String toString() {
        return "Pedido{" +
                "idUsuario=" + idUsuario +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", seEnvia=" + seEnvia +
                ", direccion='" + direccion + '\'' +
                ", precio=" + precio +
                ", cantidadPlatos=" + cantidadPlatos +
                ", fechaPedido=" + fechaPedido +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this.getId().equals( ((Pedido)obj).getId() )){
            return true;
        }
        else{
            return this.getIdUsuario().equals(((Pedido) obj).getIdUsuario())
                    && this.getEmail().equals(((Pedido) obj).getEmail())
                    && this.getSeEnvia().equals(((Pedido) obj).getSeEnvia())
                    && this.getDireccion().equals(((Pedido) obj).getDireccion())
                    && this.getPrecio().equals(((Pedido) obj).getPrecio())
                    && this.getCantidadPlatos().equals(((Pedido) obj).getCantidadPlatos())
                    && this.getFechaPedido().equals(((Pedido) obj).getFechaPedido())
                    && this.getDireccion().equals(((Pedido) obj).getDireccion())
                    && this.getDireccion().equals(((Pedido) obj).getDireccion())
                    && this.getDireccion().equals(((Pedido) obj).getDireccion());
        }
    }
}
