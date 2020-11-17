package app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.dao.DAOCuentaBancaria;
import app.dao.DAOPedido;
import app.dao.DAOPedidoConPlatos;
import app.dao.DAOPlato;
import app.dao.DAOTarjeta;
import app.dao.DAOUsuario;
import app.model.CuentaBancaria;
import app.model.Pedido;
import app.model.PedidoConPlatos;
import app.model.Plato;
import app.model.Tarjeta;
import app.model.Usuario;

@Database(entities = {Plato.class, Pedido.class, PedidoConPlatos.class, CuentaBancaria.class, Tarjeta.class, Usuario.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase _INSTANCE;

    public abstract DAOPlato daoPlato();
    public abstract DAOPedido daoPedido();
    public abstract DAOPedidoConPlatos daoPedidoConPlatos();
    public abstract DAOCuentaBancaria daoCuentaBancaria();
    public abstract DAOTarjeta daoTarjeta();
    public abstract DAOUsuario daoUsuario();

    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getInstance(final Context context) {
        if (_INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (_INSTANCE == null) {
                    _INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return _INSTANCE;
    }
}