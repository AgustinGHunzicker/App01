package app.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import app.database.Converters;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "idUsuario", onDelete = CASCADE))
public class Pedido implements Serializable {
    /* ---- RELACIONES -----*/
    @ColumnInfo(name="idUsuario")
    private long idUsuario;

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private long id;
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="seEnvia")
    private Boolean seEnvia;
    @ColumnInfo(name="direccion")
    private String direccion;
    @ColumnInfo(name="price")
    private double price;
    @ColumnInfo(name="cantidadPlatos")
    private int cantidadPlatos;
    @TypeConverters(Converters.class)
    @ColumnInfo(name="fechaoPedido")
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

    public void setPrice(double price) {
        this.price = price;
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
                ", price=" + price +
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
            if(this.getIdUsuario().equals(((Pedido)obj).getIdUsuario())
                    && this.getEmail().equals(((Pedido)obj).getEmail())
                    && this.getSeEnvia().equals(((Pedido)obj).getSeEnvia())
                    && this.getDireccion().equals(((Pedido)obj).getDireccion())
                    && this.getPrice().equals(((Pedido)obj).getPrice())
                    && this.getCantidadPlatos().equals(((Pedido)obj).getCantidadPlatos())
                    && this.getFechaPedido().equals(((Pedido)obj).getFechaPedido())
                    && this.getDireccion().equals(((Pedido)obj).getDireccion())
                    && this.getDireccion().equals(((Pedido)obj).getDireccion())
                    && this.getDireccion().equals(((Pedido)obj).getDireccion())
            ){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
