package com.example.myapplication.pruebaBaseDatos;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.convertes.Converters;
import com.example.myapplication.dao.DAOCuentaBancaria;
import com.example.myapplication.dao.DAOPedido;
import com.example.myapplication.dao.DAOPlato;
import com.example.myapplication.dao.DAOTarjeta;
import com.example.myapplication.dao.DAOUsuario;
import com.example.myapplication.model.CuentaBancaria;
import com.example.myapplication.model.Pedido;
import com.example.myapplication.model.Plato;
import com.example.myapplication.model.Tarjeta;
import com.example.myapplication.model.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Plato.class, Pedido.class, CuentaBancaria.class, Tarjeta.class, Usuario.class}, version =1)//para indicar a las entidades (tablas) que se va a acceder

@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase _INSTANCE;

    public abstract DAOPlato daoPlato();
    public abstract DAOPedido daoPedido();
    public abstract DAOCuentaBancaria daoCuentaBancaria();
    public abstract DAOTarjeta daoTarjeta();
    public abstract DAOUsuario daoUsuario();

    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getInstance(final Context context) {
        if (_INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
                }
            }
        }
        return _INSTANCE;
    }
}
