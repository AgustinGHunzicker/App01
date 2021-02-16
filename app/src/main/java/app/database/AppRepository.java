package app.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import app.dao.DAOPedido;
import app.dao.DAOPedidoConPlatos;
import app.dao.DAOPlato;
import app.dao.DAOUsuario;
import app.model.Pedido;
import app.model.PedidoConPlatos;
import app.model.Plato;
import app.model.Usuario;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository implements OnResultCallback {

    private static AppRepository _INSTANCE = null;
    private static AppDatabase db = null;
    private final OnResultCallback callback;
    protected final DAOPlato daoPlato;
    protected final DAOPedido daoPedido;
    protected final DAOPedidoConPlatos daoPedidoConPlatos;
    protected final DAOUsuario daoUsuario;

    private AppRepository(final Context context, OnResultCallback callback2){
        db = AppDatabase.getInstance(context);
        daoPlato = db.daoPlato();
        daoPedido = db.daoPedido();
        daoPedidoConPlatos = db.daoPedidoConPlatos();
        daoUsuario = db.daoUsuario();
        callback = callback2;
    }

    public static AppRepository getInstance(final Context context, OnResultCallback<?> callback) {
        if (_INSTANCE == null) {
            _INSTANCE = new AppRepository(context, callback); // se inicializa solo 1 vez
        }
        return _INSTANCE;
    }

    public static void close(){
        _INSTANCE = null;
        db.close();
    }


    /* INSERTS */
    public void insertarPlato(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() { daoPlato.insertar(plato);     }
        });
    }

    public void insertarUsuario(final Usuario usuario){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoUsuario.insertar(usuario);
            }
        });
    }
    public void insertarPedido(final Pedido pedido, final List<Plato> listaPlatos){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoPedido.insertar(pedido);
                for(Plato plato:listaPlatos){
                    PedidoConPlatos pedidoConPlatos = new PedidoConPlatos();
                    pedidoConPlatos.setIdPlato(plato.getId());
                    pedidoConPlatos.setIdPedido(pedido.getId());
                    daoPedidoConPlatos.insertar(pedidoConPlatos);
                }
            }
        });
    }


    /* TAREAS ASINCRONAS */
    public void buscarPlato(long id) {new BuscarPlatoById(daoPlato, this, id).execute();  }
    public void buscarPlatos() {
        new BuscarListaPlato(daoPlato, this).execute();
    }
    public void buscarPedidos() { new BuscarListaPedido(daoPedido, this).execute(); }

    @Override
    public void onResult(List result) {
        Log.d("DEBUG", "Entity found");
        callback.onResult(result);
    }
}


/* ----------------- ASYNC TASKS ----------------- */

class BuscarPlatoById extends AsyncTask<String, Void, List<Plato>> {
    private final DAOPlato dao;
    private final OnResultCallback<Plato> callback;

    public BuscarPlatoById(DAOPlato dao, OnResultCallback context, long id) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Plato> doInBackground(String... strings) {
        return dao.buscarTodos();
    }

    @Override
    protected void onPostExecute(List<Plato> platos) {
        super.onPostExecute(platos);
        callback.onResult(platos);
    }
}

class BuscarListaPlato extends AsyncTask<String, Void, List<Plato>> {
    private final DAOPlato dao;
    private final OnResultCallback<Plato> callback;

    public BuscarListaPlato(DAOPlato dao, OnResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Plato> doInBackground(String... strings) {
        return dao.buscarTodos();
    }

    @Override
    protected void onPostExecute(List<Plato> platos) {
        super.onPostExecute(platos);
        callback.onResult(platos);
    }
}

class BuscarListaPedido extends AsyncTask<String, Void, List<Pedido>> {
    private final DAOPedido dao;
    private final OnResultCallback<Pedido> callback;

    public BuscarListaPedido(DAOPedido dao, OnResultCallback<Pedido> context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        return dao.buscarTodos();
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        super.onPostExecute(pedidos);
        callback.onResult(pedidos);
    }
}