package app.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Plato implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPlato")
    private long idPlato;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "precio")
    private Double precio;

    @ColumnInfo(name = "calorias")
    private int calorias;

    @ColumnInfo(name = "fotoUrl")
    private String fotoUrl;

    public Plato() {
        titulo = "";
        descripcion = "";
        fotoUrl = "";
    }

    public Long getIdPlato() {
        return idPlato;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public void setIdPlato(long idPlato) {
        this.idPlato = idPlato;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return titulo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this.getIdPlato().equals( ((Plato)obj).getIdPlato() )){
            return true;
        }
        else{
            if(this.titulo.equals( ((Plato)obj).getTitulo() )){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
