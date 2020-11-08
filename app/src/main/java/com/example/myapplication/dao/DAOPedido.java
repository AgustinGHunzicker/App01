package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Pedido;

import java.util.List;

@Dao
public interface DAOPedido {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedido WHERE id = :id LIMIT 1")
    Pedido buscar(String id);

    @Query("SELECT * FROM pedido")
    List<Pedido> buscarTodos();
}
