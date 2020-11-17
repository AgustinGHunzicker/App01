package test;

import android.content.Context;
import android.os.Build;

import androidx.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.time.LocalDate;
import java.util.List;

import app.dao.DAOPedido;
import app.dao.DAOPedidoConPlatos;
import app.dao.DAOPlato;
import app.dao.DAOUsuario;
import app.database.AppDatabase;
import app.model.Pedido;
import app.model.PedidoConPlatos;
import app.model.Plato;
import app.model.Usuario;

import androidx.test.core.app.ApplicationProvider;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@Config(sdk = {Build.VERSION_CODES.O_MR1})
@RunWith(RobolectricTestRunner.class)
public class PedidoDaoTest {
    private DAOPedido daoPedido;
    private DAOPlato daoPlato;
    private DAOPedidoConPlatos daoPedidoConPlatos;
    private DAOUsuario daoUsuario;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        daoPedido = db.daoPedido();
        daoPlato = db.daoPlato();
        daoPedidoConPlatos = db.daoPedidoConPlatos();
        daoUsuario = db.daoUsuario();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertPedidoTest() {
        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);

        Long idUser = daoUsuario.insertar(user);
        Usuario user2 = daoUsuario.buscar(idUser);
        assertEquals(user, user2);


        Pedido p = new Pedido();
        p.setCantidadPlatos(5);
        p.setDireccion("Dirección prueba");
        p.setPrice(1000d);
        p.setEmail("usuarioprueba@mail.com");
        p.setFechaPedido(LocalDate.now());
        p.setSeEnvia(true);
        p.setIdUsuario(idUser);

        Long id = daoPedido.insertar(p);
        Pedido p2 = daoPedido.buscar(id);
        assertEquals(p, p2);

        System.out.println(p2);
    }

    @Test
    public void borrarPedidoTest() {
        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);

        Long idUser = daoUsuario.insertar(user);
        Usuario user2 = daoUsuario.buscar(idUser);
        assertEquals(user, user2);


        Pedido p = new Pedido();
        p.setCantidadPlatos(5);
        p.setDireccion("Dirección prueba");
        p.setPrice(1000d);
        p.setEmail("usuarioprueba@mail.com");
        p.setFechaPedido(LocalDate.now());
        p.setSeEnvia(true);
        p.setIdUsuario(idUser);

        Long id = daoPedido.insertar(p);
        Pedido p2 = daoPedido.buscar(id);
        assertEquals(p, p2);


        daoPedido.borrar(p2);
        Pedido pedidoBuscado = daoPedido.buscar(p2.getId());
        assertNull(pedidoBuscado);

        System.out.println(p2);
    }


    @Test
    public void actualizarPedidoTest() {
        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);

        Long idUser = daoUsuario.insertar(user);
        Usuario user2 = daoUsuario.buscar(idUser);
        assertEquals(user, user2);

        Pedido p = new Pedido();
        p.setCantidadPlatos(5);
        p.setDireccion("Dirección prueba");
        p.setPrice(1000d);
        p.setEmail("usuarioprueba@mail.com");
        p.setFechaPedido(LocalDate.now());
        p.setSeEnvia(true);
        p.setIdUsuario(idUser);

        Long id = daoPedido.insertar(p);
        Pedido p2 = daoPedido.buscar(id);
        assertEquals(p, p2);

        p2.setCantidadPlatos(10);
        p2.setDireccion("Dirección prueba UPDATE");
        p2.setPrice(2000d);
        p2.setEmail("usuariopruebaUPDATE@mail.com");
        p2.setFechaPedido(LocalDate.now().plusDays(1));
        p2.setSeEnvia(false);
        p2.setIdUsuario(idUser);


        daoPedido.actualizar(p2);
        assertEquals(p2.getCantidadPlatos().toString(), "10");
        assertEquals(p2.getDireccion(), "Dirección prueba UPDATE");
        assertEquals(p2.getPrice(), 2000d);
        assertEquals(p2.getEmail(), "usuariopruebaUPDATE@mail.com");
        assertEquals(p2.getFechaPedido(), LocalDate.now().plusDays(1));
        assertEquals(p2.getSeEnvia().toString(), "false");
        assertEquals(p2.getIdUsuario(), idUser);

        System.out.println(p2);
    }


    @Test
    public void buscarTodosTest() {
        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);

        Long idUser = daoUsuario.insertar(user);
        Usuario user2 = daoUsuario.buscar(idUser);
        assertEquals(user, user2);


        Pedido p = new Pedido();
        p.setCantidadPlatos(5);
        p.setDireccion("Dirección prueba1");
        p.setPrice(1000d);
        p.setEmail("usuarioprueba1@mail.com");
        p.setFechaPedido(LocalDate.now());
        p.setSeEnvia(true);
        p.setIdUsuario(idUser);
        daoPedido.insertar(p);

        Pedido p2 = new Pedido();
        p2.setCantidadPlatos(5);
        p2.setDireccion("Dirección prueba2");
        p2.setPrice(1000d);
        p2.setEmail("usuarioprueba2@mail.com");
        p2.setFechaPedido(LocalDate.now());
        p2.setSeEnvia(true);
        p2.setIdUsuario(idUser);
        daoPedido.insertar(p2);

        Pedido p3 = new Pedido();
        p3.setCantidadPlatos(5);
        p3.setDireccion("Dirección prueba3");
        p3.setPrice(1000d);
        p3.setEmail("usuarioprueba3@mail.com");
        p3.setFechaPedido(LocalDate.now());
        p3.setSeEnvia(true);
        p3.setIdUsuario(idUser);
        daoPedido.insertar(p3);

        Pedido p4 = new Pedido();
        p4.setCantidadPlatos(5);
        p4.setDireccion("Dirección prueb4a");
        p4.setPrice(1000d);
        p4.setEmail("usuarioprueba4@mail.com");
        p4.setFechaPedido(LocalDate.now());
        p4.setSeEnvia(true);
        p4.setIdUsuario(idUser);
        daoPedido.insertar(p4);


        List<Pedido> lista = daoPedido.buscarTodos();
        for (Pedido pedido:lista) {
            System.out.println(pedido.toString());
        }
        assertEquals(lista.size(), 4);
    }


    @Test
    public void buscarPlatosPedidoTest() {
        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);

        Long idUser = daoUsuario.insertar(user);
        Usuario user2 = daoUsuario.buscar(idUser);
        assertEquals(user, user2);


        Pedido p = new Pedido();
        p.setCantidadPlatos(5);
        p.setDireccion("Dirección prueba1");
        p.setPrice(1000d);
        p.setEmail("usuarioprueba1@mail.com");
        p.setFechaPedido(LocalDate.now());
        p.setSeEnvia(true);
        p.setIdUsuario(idUser);
        Long idPedido = daoPedido.insertar(p);


        Plato newPlate1 = new Plato();
        newPlate1.setTitle("Hamburguesa1");
        newPlate1.setDescription("Un plato poco saludable pero rico");
        newPlate1.setPrice(200d);
        newPlate1.setCalories(500);
        Long idPlato1 = daoPlato.insertar(newPlate1);

        Plato newPlate2 = new Plato();
        newPlate2.setTitle("Hamburguesa2");
        newPlate2.setDescription("Un plato poco saludable pero feo");
        newPlate2.setPrice(300d);
        newPlate2.setCalories(501);
        Long idPlato2 = daoPlato.insertar(newPlate2);

        Plato newPlate3 = new Plato();
        newPlate3.setTitle("Hamburguesa3");
        newPlate3.setDescription("Un plato poco saludable pero mehj");
        newPlate3.setPrice(400d);
        newPlate3.setCalories(502);
        Long idPlato3 = daoPlato.insertar(newPlate3);

        Plato newPlato4 = new Plato();
        newPlato4.setTitle("Hamburguesa4");
        newPlato4.setDescription("Un plato");
        newPlato4.setPrice(500d);
        newPlato4.setCalories(502);
        Long idPlato4 = daoPlato.insertar(newPlato4);


        PedidoConPlatos pedido1 = new PedidoConPlatos();
        pedido1.setIdPedido(idPedido);
        pedido1.setIdPlato(idPlato1);
        daoPedidoConPlatos.insertar(pedido1);

        PedidoConPlatos pedido2 = new PedidoConPlatos();
        pedido2.setIdPedido(idPedido);
        pedido2.setIdPlato(idPlato2);
        daoPedidoConPlatos.insertar(pedido2);


        PedidoConPlatos pedido3 = new PedidoConPlatos();
        pedido3.setIdPedido(idPedido);
        pedido3.setIdPlato(idPlato3);
        daoPedidoConPlatos.insertar(pedido3);


        PedidoConPlatos pedido4 = new PedidoConPlatos();
        pedido4.setIdPedido(idPedido);
        pedido4.setIdPlato(idPlato4);
        daoPedidoConPlatos.insertar(pedido4);


        List<Plato> lista = daoPedido.buscarPlatosPedidos(idPedido);
        for (Plato plato:lista) {
            System.out.println(plato.toString());
        }
        assertEquals(lista.size(), 4);
    }
}

