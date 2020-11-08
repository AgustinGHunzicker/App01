package com.example.myapplication.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.PedidoConPlatos;
import com.example.myapplication.model.Plato;

import java.util.List;

public interface DAOPedidoConPlatos {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(PedidoConPlatos pedidoConPlatos);

    @Delete
    void borrar(PedidoConPlatos pedidoConPlatos);

    @Update
    void actualizar(PedidoConPlatos pedidoConPlatos);

    @Query("SELECT * FROM pedidoConPlatos WHERE id = :id LIMIT 1")
    PedidoConPlatos buscar(String id);

    @Query("SELECT * FROM pedidoConPlatos")
    List<PedidoConPlatos> buscarTodos();

    @Query("SELECT * FROM pedidoConPlatos WHERE idPedido = :idPedido")
    List<Plato> buscarPlatosPedidos(String idPedido);
}
