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

@Entity(foreignKeys = @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "idUsuario",
        onDelete = CASCADE))
public class Tarjeta implements Serializable {
    /* ---- RELACIONES -----*/
    @ColumnInfo(name="idUsuario")
    private long idUsuario;

    /* ---- ATRIBUTOS -----*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private long id;
    @ColumnInfo(name="numero")
    private String numero;
    @ColumnInfo(name="ccv")
    private String ccv;
    @TypeConverters(Converters.class)
    @ColumnInfo(name="vencimiento")
    private LocalDate vencimiento;
    @ColumnInfo(name="esCredito")
    private Boolean esCredito;

    public Tarjeta () {}

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Boolean getEsCredito() {
        return esCredito;
    }

    public void setEsCredito(Boolean esCredito) {
        this.esCredito = esCredito;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this.getId().equals( ((Tarjeta)obj).getId() )){
            return true;
        }
        else{
            if(this.numero.equals( ((Tarjeta)obj).getNumero() )){
                return true;
            }
            else{
                return false;
            }
        }
    }
}