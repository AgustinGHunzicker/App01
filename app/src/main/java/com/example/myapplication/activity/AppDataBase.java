package com.example.myapplication.activity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.dao.DAOPlato;
import com.example.myapplication.model.Plato;

@Database(entities = {Plato.class}, version =1)//para indicar a las entidades (tablas) que se va a acceder


public abstract class AppDataBase extends RoomDatabase {
    //funciones que nos van a devolver los dao que implemente
    private static AppDataBase sInstance; // variable para poder llamar al manejador de bd
    public abstract DAOPlato platoDao(); //para indicar que permisos va a tener (listar, agregar, actualizar, etc)

    //para crear la instancia de la BD





}
