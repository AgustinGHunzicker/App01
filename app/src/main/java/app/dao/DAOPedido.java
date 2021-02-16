package app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import app.model.Pedido;
import app.model.Plato;

import java.util.List;

@Dao
public interface DAOPedido {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedido WHERE id = :id LIMIT 1")
    Pedido buscar(long id);

    @Query("SELECT * FROM pedido")
    List<Pedido> buscarTodos();

    @Query("SELECT pl.* FROM pedidoConPlatos pe, plato pl WHERE pe.idPedido = :idPedido and pe.idPlato=pl.id")
    List<Plato> buscarPlatosPedidos(long idPedido);
}
