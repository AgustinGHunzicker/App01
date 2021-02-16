package app.dao;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import app.model.PedidoConPlatos;
import app.model.Plato;

import java.util.List;

@Dao
public interface DAOPedidoConPlatos {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertar(PedidoConPlatos pedidoConPlatos);

    @Delete
    void borrar(PedidoConPlatos pedidoConPlatos);

    @Update
    void actualizar(PedidoConPlatos pedidoConPlatos);

    @Query("SELECT * FROM pedidoConPlatos WHERE id = :id LIMIT 1")
    PedidoConPlatos buscar(long id);

    @Query("SELECT * FROM pedidoConPlatos")
    List<PedidoConPlatos> buscarTodos();
}
