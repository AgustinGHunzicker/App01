package app.database;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import app.dao.DAOPedido;
import app.dao.DAOPedidoConPlatos;
import app.dao.DAOPlato;
import app.dao.DAOUsuario;
import app.model.Pedido;
import app.model.PedidoConPlatos;
import app.model.Plato;
import app.model.Usuario;

public class AppRepository implements OnResultCallback {

    private static AppRepository _INSTANCE = null;
    private static AppDatabase db = null;
    private static AppCompatActivity _CONTEXT;
    private final OnResultCallback callback;

    protected final DAOPlato daoPlato;
    protected final DAOPedido daoPedido;
    protected final DAOPedidoConPlatos daoPedidoConPlatos;
    protected final DAOUsuario daoUsuario;

    public AppRepository(final AppCompatActivity context, OnResultCallback callback2){
        _CONTEXT = context;
        db = AppDatabase.getInstance(_CONTEXT);
        daoPlato = db.daoPlato();
        daoPedido = db.daoPedido();
        daoPedidoConPlatos = db.daoPedidoConPlatos();
        daoUsuario = db.daoUsuario();
        callback = callback2;
    }

    /*public static AppRepository getInstance(final AppCompatActivity context, OnResultCallback<?> callback) {
        if (_INSTANCE == null) _INSTANCE = new AppRepository(context, callback);
        return _INSTANCE;
    }*/

    public static void close(){
        _INSTANCE = null;
        //db.close();
    }

    public void insertarPlato(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoPlato.insertar(plato);
            }
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

    public void buscarPlatos() {
        new BuscarListaPlato(daoPlato, this).execute();
    }

    @Override
    public void onResult(List result) {
        Log.d("DEBUG", "Entity found");
        callback.onResult(result);
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