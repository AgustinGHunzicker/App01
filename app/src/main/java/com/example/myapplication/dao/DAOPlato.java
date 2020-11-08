package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Plato;

import java.util.List;

@Dao
public interface  DAOPlato {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(Plato plato); //Devuelve 1 cuando es correcto y 0 cuando no

    @Delete
    void borrar(Plato plato);

    @Update
    void actualizar(Plato plato);

    @Query("SELECT * FROM plato WHERE id = :id LIMIT 1")
    Plato buscar(String id);

    @Query("SELECT * FROM plato")
    List<Plato> buscarTodos();

    @Query("SELECT * FROM plato WHERE price > :precioMinimo")
    public Plato[] platosPreciosMayorA(int precioMinimo);
}
