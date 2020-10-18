package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Tarjeta;

import java.util.List;

@Dao
public interface DAOTarjeta {
    @Insert
    void insertar(Tarjeta tarjeta);

    @Delete
    void borrar(Tarjeta tarjeta);

    @Update
    void actualizar(Tarjeta tarjeta);

    @Query("SELECT * FROM tarjeta WHERE id = :id LIMIT 1")
    Tarjeta buscar(String id);

    @Query("SELECT * FROM tarjeta")
    List<Tarjeta> buscarTodos();
}
