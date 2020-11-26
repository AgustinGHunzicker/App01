package app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import app.model.Plato;

import java.util.List;

@Dao
public interface  DAOPlato {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertar(Plato plato);

    @Delete
    void borrar(Plato plato);

    @Update
    void actualizar(Plato plato);

    @Query("SELECT * FROM plato WHERE id = :id LIMIT 1")
    Plato buscar(long id);

    @Query("SELECT * FROM plato")
    List<Plato> buscarTodos();

    @Query("SELECT * FROM plato WHERE price > :precioMinimo")
    public Plato[] platosPreciosMayorA(double precioMinimo);
}