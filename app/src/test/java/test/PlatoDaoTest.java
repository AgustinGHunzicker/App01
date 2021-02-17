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

import java.util.List;

import app.dao.DAOPlato;
import app.database.AppDatabase;
import app.model.Plato;

import androidx.test.core.app.ApplicationProvider;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@Config(sdk = {Build.VERSION_CODES.O_MR1})
@RunWith(RobolectricTestRunner.class)
public class PlatoDaoTest {
    private DAOPlato daoPlato;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        daoPlato = db.daoPlato();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertPlatoTest() {
        Plato newPlate = new Plato();
        newPlate.setTitulo("Hamburguesa");
        newPlate.setDescripcion("Un plato poco saludable pero rico");
        newPlate.setPrecio(349.0d);
        newPlate.setCalorias(500);
        Long id = daoPlato.insertar(newPlate);
        Plato p = daoPlato.buscar(id);
        System.out.println(p);
        assertEquals(p, newPlate);
    }

    @Test
    public void borrarPlatoTest()  {
        Plato newPlate = new Plato();
        newPlate.setTitulo("Hamburguesa");
        newPlate.setDescripcion("Un plato poco saludable pero rico");
        newPlate.setPrecio(349.0d);
        newPlate.setCalorias(500);
        Long id = daoPlato.insertar(newPlate);
        Plato p = daoPlato.buscar(id);
        assertEquals(p, newPlate);
        daoPlato.borrar(p);

        Plato platoBuscado = daoPlato.buscar(p.getId());
        assertNull(platoBuscado);
    }

    @Test
    public void actualizarPlatoTest()  {
        Plato newPlate = new Plato();
        newPlate.setTitulo("Hamburguesa");
        newPlate.setDescripcion("Un plato poco saludable pero rico");
        newPlate.setPrecio(349.0d);
        newPlate.setCalorias(500);
        Long id = daoPlato.insertar(newPlate);
        Plato p = daoPlato.buscar(id);
        assertEquals(p, newPlate);
        p.setTitulo("Hamburguesa modificada");
        daoPlato.actualizar(p);
        assertEquals(p.getTitulo(), "Hamburguesa modificada");
    }

    @Test
    public void buscarTodosTest(){
        Plato newPlate1 = new Plato();
        newPlate1.setTitulo("Hamburguesa1");
        newPlate1.setDescripcion("Un plato poco saludable pero rico");
        newPlate1.setPrecio(350.0d);
        newPlate1.setCalorias(500);
        daoPlato.insertar(newPlate1);

        Plato newPlate2 = new Plato();
        newPlate2.setTitulo("Hamburguesa2");
        newPlate2.setDescripcion("Un plato poco saludable pero feo");
        newPlate2.setPrecio(351.0d);
        newPlate2.setCalorias(501);
        daoPlato.insertar(newPlate2);

        Plato newPlate3 = new Plato();
        newPlate3.setTitulo("Hamburguesa3");
        newPlate3.setDescripcion("Un plato poco saludable pero mehj");
        newPlate3.setPrecio(352.0d);
        newPlate3.setCalorias(502);
        daoPlato.insertar(newPlate3);

        List<Plato> lista = daoPlato.buscarTodos();
        for (Plato p:lista) {
            System.out.println(p.toString());
        }
        assertEquals(lista.size(), 3);
    }

    @Test
    public void platosPreciosMayorATest() {
        Plato newPlate1 = new Plato();
        newPlate1.setTitulo("Hamburguesa1");
        newPlate1.setDescripcion("Un plato poco saludable pero rico");
        newPlate1.setPrecio(200d);
        newPlate1.setCalorias(500);
        daoPlato.insertar(newPlate1);

        Plato newPlate2 = new Plato();
        newPlate2.setTitulo("Hamburguesa2");
        newPlate2.setDescripcion("Un plato poco saludable pero feo");
        newPlate2.setPrecio(300d);
        newPlate2.setCalorias(501);
        daoPlato.insertar(newPlate2);

        Plato newPlate3 = new Plato();
        newPlate3.setTitulo("Hamburguesa3");
        newPlate3.setDescripcion("Un plato poco saludable pero mehj");
        newPlate3.setPrecio(400d);
        newPlate3.setCalorias(502);
        daoPlato.insertar(newPlate3);

        Plato newPlato4 = new Plato();
        newPlato4.setTitulo("Hamburguesa4");
        newPlato4.setDescripcion("Un plato");
        newPlato4.setPrecio(500d);
        newPlato4.setCalorias(502);
        daoPlato.insertar(newPlato4);

        List<Plato> lista = daoPlato.buscarTodos();
        System.out.println("TODOS LOS PLATOS");
        for (Plato p:lista) {
            System.out.println(p.toString());
        }
        assertEquals(lista.size(), 4);

        Plato[] platos = daoPlato.platosPreciosMayorA(301d);
        System.out.println("TODOS LOS PLATOS CON PRECIO MAYOR A 301");
        for (Plato p:platos) {
            System.out.println(p.toString());
        }
        assertEquals(platos.length, 2);
    }

}
