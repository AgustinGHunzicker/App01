package app.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable {

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private long id;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="clave")
    private String clave;
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="credito")
    private double credito;

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this.getId().equals( ((Usuario)obj).getId() )){
            return true;
        }
        else{
            if(this.nombre.equals( ((Usuario)obj).getNombre() )
                    && this.clave.equals( ((Usuario)obj).getClave() )
                    && this.email.equals( ((Usuario)obj).getEmail() )
                    && this.getCredito().equals( ((Usuario)obj).getCredito()
            )){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
