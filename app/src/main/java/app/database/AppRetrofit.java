package app.database;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.dao.PedidoService;
import app.dao.PlatoService;
import app.model.Pedido;
import app.model.Plato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {
    private static final String _SERVER = "http://10.0.2.2:3001/";//= "http://localhost:3001/";
    private static AppRetrofit _RETROFIT;
    private static Retrofit retrofit;
    private static PlatoService platoService;
    private static PedidoService pedidoService;

    private AppRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        platoService = retrofit.create(PlatoService.class);
        pedidoService = retrofit.create(PedidoService.class);
    }

    public static AppRetrofit getRetrofit(){
        if(_RETROFIT == null){
            _RETROFIT = new AppRetrofit();
        }
        return _RETROFIT;
    }

    public void insertPlato(Plato plato){
        platoService.crearPlato(plato).enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                Log.d("on AppRetrofit","Plato correctamente guardado por Retrofit.");
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("on AppRetrofit","Error en el guardado del plato", t);
            }
        });
    }

    public void insertPedido(Pedido p){
        pedidoService.crearPedido(p).enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                Log.d("on AppRetrofit","Pedido guardado por Retrofit.");
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("on AppRetrofit","Falló el recupero de la lista de platos", t);
            }
        });
    }

    public void listaPlatos(){
        platoService.listarTodos().enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    List<Plato> list = new ArrayList<>();
                    list.addAll(response.body());
                    Log.d("on AppRetrofit","lista de platos de "+list.size()+" elementos");
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("ListaPlatos","falló", t);
            }
        });
    }
}
