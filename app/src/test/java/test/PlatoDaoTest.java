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
        newPlate.setTitle("Hamburguesa");
        newPlate.setDescription("Un plato poco saludable pero rico");
        newPlate.setPrice(349.0d);
        newPlate.setCalories(500);
        Long id = daoPlato.insertar(newPlate);
        Plato p = daoPlato.buscar(id);
        System.out.println(p);
        assertEquals(p, newPlate);
    }

    @Test
    public void borrarPlatoTest()  {
        Plato newPlate = new Plato();
        newPlate.setTitle("Hamburguesa");
        newPlate.setDescription("Un plato poco saludable pero rico");
        newPlate.setPrice(349.0d);
        newPlate.setCalories(500);
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
        newPlate.setTitle("Hamburguesa");
        newPlate.setDescription("Un plato poco saludable pero rico");
        newPlate.setPrice(349.0d);
        newPlate.setCalories(500);
        Long id = daoPlato.insertar(newPlate);
        Plato p = daoPlato.buscar(id);
        assertEquals(p, newPlate);
        p.setTitle("Hamburguesa modificada");
        daoPlato.actualizar(p);
        assertEquals(p.getTitle(), "Hamburguesa modificada");
    }

    @Test
    public void buscarTodosTest(){
        Plato newPlate1 = new Plato();
        newPlate1.setTitle("Hamburguesa1");
        newPlate1.setDescription("Un plato poco saludable pero rico");
        newPlate1.setPrice(350.0d);
        newPlate1.setCalories(500);
        daoPlato.insertar(newPlate1);

        Plato newPlate2 = new Plato();
        newPlate2.setTitle("Hamburguesa2");
        newPlate2.setDescription("Un plato poco saludable pero feo");
        newPlate2.setPrice(351.0d);
        newPlate2.setCalories(501);
        daoPlato.insertar(newPlate2);

        Plato newPlate3 = new Plato();
        newPlate3.setTitle("Hamburguesa3");
        newPlate3.setDescription("Un plato poco saludable pero mehj");
        newPlate3.setPrice(352.0d);
        newPlate3.setCalories(502);
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
        newPlate1.setTitle("Hamburguesa1");
        newPlate1.setDescription("Un plato poco saludable pero rico");
        newPlate1.setPrice(200d);
        newPlate1.setCalories(500);
        daoPlato.insertar(newPlate1);

        Plato newPlate2 = new Plato();
        newPlate2.setTitle("Hamburguesa2");
        newPlate2.setDescription("Un plato poco saludable pero feo");
        newPlate2.setPrice(300d);
        newPlate2.setCalories(501);
        daoPlato.insertar(newPlate2);

        Plato newPlate3 = new Plato();
        newPlate3.setTitle("Hamburguesa3");
        newPlate3.setDescription("Un plato poco saludable pero mehj");
        newPlate3.setPrice(400d);
        newPlate3.setCalories(502);
        daoPlato.insertar(newPlate3);

        Plato newPlato4 = new Plato();
        newPlato4.setTitle("Hamburguesa4");
        newPlato4.setDescription("Un plato");
        newPlato4.setPrice(500d);
        newPlato4.setCalories(502);
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
