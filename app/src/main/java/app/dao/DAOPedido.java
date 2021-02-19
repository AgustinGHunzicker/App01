package app.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import app.model.Pedido;
import app.model.Plato;

@Dao
public interface DAOPedido {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedido WHERE idPedido = :idPedido LIMIT 1")
    Pedido buscar(long idPedido);

    @Query("SELECT * FROM pedido")
    List<Pedido> buscarTodos();

    @Query("SELECT pl.* FROM pedidoConPlatos pe, plato pl WHERE pe.idPedido = :idPedido and pe.idPlato=pl.idPlato")
    List<Plato> buscarPlatosPedidos(long idPedido);
}
