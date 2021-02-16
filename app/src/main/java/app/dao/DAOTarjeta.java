package app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import app.model.Tarjeta;

@Dao
public interface DAOTarjeta {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertar(Tarjeta tarjeta);

    @Delete
    void borrar(Tarjeta tarjeta);

    @Update
    void actualizar(Tarjeta tarjeta);

    @Query("SELECT * FROM tarjeta WHERE id = :id LIMIT 1")
    Tarjeta buscar(long id);

    @Query("SELECT * FROM tarjeta")
    List<Tarjeta> buscarTodos();
}
