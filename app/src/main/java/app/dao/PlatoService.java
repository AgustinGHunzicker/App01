package app.dao;

import java.util.List;

import app.model.Plato;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlatoService {
    @POST("platos/")
    Call<Plato> crearPlato(@Body Plato p);

    @GET("platos/")
    Call<List<Plato>> listarTodos();

    @GET("platos/")
    Call<List<Plato>> buscarPlatoPorID(@Query("id") Integer id);

    @GET("platos/")
    Call<List<Plato>> buscarPlatoPorNombre(@Query("nombre") String nombre);

    @GET("platos/")
    Call<List<Plato>> buscarPlatoPorPrecios(@Query("precio_gte") Integer precioMinimo, @Query("precio_lte") Integer precioMaximo);

    @PUT("platos/{ID}")
    Call<Plato> actualizarPlato(@Path("ID") Integer id, @Body Plato plato);

    @DELETE("platos/{ID}")
    Call<Void> eliminarPlato(@Path("ID") Integer id);
}
