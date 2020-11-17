package app.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private long idUsuario;

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private long id;
    @ColumnInfo(name="cbu")
    private String cbu;
    @ColumnInfo(name="alias")
    private String alias;

    public CuentaBancaria () {}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this.getId().equals( ((CuentaBancaria)obj).getId() )){
            return true;
        }
        else{
            if(this.cbu.equals( ((CuentaBancaria)obj).getCbu() )){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
