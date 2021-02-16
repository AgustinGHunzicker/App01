package app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import app.model.CuentaBancaria;

@Dao
public interface DAOCuentaBancaria {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertar(CuentaBancaria cuentaBancaria);

    @Delete
    void borrar(CuentaBancaria cuentaBancaria);

    @Update
    void actualizar(CuentaBancaria cuentaBancaria);

    @Query("SELECT * FROM cuentaBancaria WHERE id = :id LIMIT 1")
    CuentaBancaria buscar(long id);

    @Query("SELECT * FROM cuentaBancaria")
    List<CuentaBancaria> buscarTodos();
}
