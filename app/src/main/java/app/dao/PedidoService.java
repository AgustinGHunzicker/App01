package app.dao;

import app.model.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoService {
    @POST("pedidos/")
    Call<Pedido> crearPedido(@Body Pedido p);
}
