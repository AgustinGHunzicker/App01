package com.example.myapplication.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.dao.DAOCuentaBancaria;
import com.example.myapplication.dao.DAOPedido;
import com.example.myapplication.dao.DAOPedidoConPlatos;
import com.example.myapplication.dao.DAOPlato;
import com.example.myapplication.dao.DAOTarjeta;
import com.example.myapplication.dao.DAOUsuario;
import com.example.myapplication.model.CuentaBancaria;
import com.example.myapplication.model.Pedido;
import com.example.myapplication.model.PedidoConPlatos;
import com.example.myapplication.model.Plato;
import com.example.myapplication.model.Tarjeta;
import com.example.myapplication.model.Usuario;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


abstract class AppDatabase extends RoomDatabase {
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
                    _INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase").build();
                }
            }
        }
        return _INSTANCE;
    }
}


public class AppRepository implements OnResultCallback {

    private static AppRepository _INSTANCE= null;
    private DAOPlato daoPlato;
    private DAOPedido daoPedido;
    private DAOPedidoConPlatos daoPedidoConPlatos;
    private DAOCuentaBancaria daoCuentaBancaria;
    private DAOTarjeta daoTarjeta;
    private DAOUsuario daoUsuario;
    private OnResultCallback callback;

    private AppRepository(final Context context, OnResultCallback callback2){
        AppDatabase db = AppDatabase.getInstance(context);
        daoPlato = db.daoPlato();
        daoPedido = db.daoPedido();
        daoPedidoConPlatos = db.daoPedidoConPlatos();
        daoCuentaBancaria = db.daoCuentaBancaria();
        daoTarjeta = db.daoTarjeta();
        daoUsuario = db.daoUsuario();
        callback = callback2;
    }
    public static AppRepository getInstance(final Context context, OnResultCallback callback) {
        if (_INSTANCE == null) {
            _INSTANCE = new AppRepository(context, callback); // se inicializa solo 1 vez
        }
        return _INSTANCE;
    }

    public void insertar(final Object object, final Class clase){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (Plato.class.equals(clase)) {
                    daoPlato.insertar((Plato) object);
                }

                if (Pedido.class.equals(clase)) {
                    daoPedido.insertar((Pedido) object);
                }

                if (CuentaBancaria.class.equals(clase)) {
                    daoCuentaBancaria.insertar((CuentaBancaria) object);
                }

                if (Tarjeta.class.equals(clase)) {
                    daoTarjeta.insertar((Tarjeta) object);
                }

                if (Usuario.class.equals(clase)) {
                    daoUsuario.insertar((Usuario) object);
                }
            }
        });
    }
    public void insertarPedido(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoPedido.insertar(pedido);
            }
        });
    }

    public void insertarPedidoConPlatos(final PedidoConPlatos pedidoConPlatos) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoPedidoConPlatos.insertar(pedidoConPlatos);
            }
        });
    }

    public void borrar(final Object object, final Class clase){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (Plato.class.equals(clase)) {
                    daoPlato.borrar((Plato) object);
                }

                if (Pedido.class.equals(clase)) {
                    daoPedido.borrar((Pedido) object);
                }

                if (CuentaBancaria.class.equals(clase)) {
                    daoCuentaBancaria.borrar((CuentaBancaria) object);
                }

                if (Tarjeta.class.equals(clase)) {
                    daoTarjeta.borrar((Tarjeta) object);
                }

                if (Usuario.class.equals(clase)) {
                    daoUsuario.borrar((Usuario) object);
                }
            }
        });
    }

    public void actualizar(final Object object, final Class clase){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (Plato.class.equals(clase)) {
                    daoPlato.actualizar((Plato) object);
                }

                if (Pedido.class.equals(clase)) {
                    daoPedido.actualizar((Pedido) object);
                }

                if (CuentaBancaria.class.equals(clase)) {
                    daoCuentaBancaria.actualizar((CuentaBancaria) object);
                }

                if (Tarjeta.class.equals(clase)) {
                    daoTarjeta.actualizar((Tarjeta) object);
                }

                if (Usuario.class.equals(clase)) {
                    daoUsuario.actualizar((Usuario) object);
                }
            }
        });
    }

    public void buscarPlato(String id) {
        //new BuscarPlatoById(daoPlato, this).execute(id);
    }

    public void buscarPlatos() {
        new BuscarPlato(daoPlato, this).execute();
    }

    public void buscarPedidos() {
        new BuscarPedido(daoPedido, this).execute();
    }

    @Override
    public void onResult(List result) {
        Log.d("DEBUG", "Entity found");
        callback.onResult(result);
    }
}

class BuscarPlato extends AsyncTask<String, Void, List<Plato>> {
    private DAOPlato dao;
    private OnResultCallback callback;

    public BuscarPlato(DAOPlato dao, OnResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Plato> doInBackground(String... strings) {
        List<Plato> platos = dao.buscarTodos();
        return platos;
    }

    @Override
    protected void onPostExecute(List<Plato> platos) {
        super.onPostExecute(platos);
        callback.onResult(platos);
    }
}

class BuscarPedido extends AsyncTask<String, Void, List<Pedido>> {
    private DAOPedido dao;
    private OnResultCallback callback;

    public BuscarPedido(DAOPedido dao, OnResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        List<Pedido> pedidos = dao.buscarTodos();
        return pedidos;
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        super.onPostExecute(pedidos);
        callback.onResult(pedidos);
    }
}