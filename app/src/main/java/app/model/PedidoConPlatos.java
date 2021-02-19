package app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PedidoConPlatos implements Serializable{
    private long idPlato;
    private long idPedido;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    public PedidoConPlatos() {}

    public long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(long idPlato) {
        this.idPlato = idPlato;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
