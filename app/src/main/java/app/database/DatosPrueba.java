package app.database;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import app.model.Plato;
import app.model.Usuario;

public class DatosPrueba {

    public DatosPrueba (AppCompatActivity context){
        //AppDatabase db = AppDatabase.getInstance(context);//Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();

        AppRepository repository = new AppRepository(context, null);


        Plato plato1 = new Plato();
        plato1.setPrecio(199.99);
        plato1.setTitulo("Hamburguesa completa");
        plato1.setCalorias(349);
        plato1.setDescripcion("Hamburguesa de carne vacuna, con cebolla, queso chedar, aderezos y lechuga.");
        repository.daoPlato.insertar(plato1);

        Plato plato2 = new Plato();
        plato2.setPrecio(150.00);
        plato2.setTitulo("Ensalada rusa");
        plato2.setCalorias(96);
        plato2.setDescripcion("Un tipo de ensalada que tiene cosas que la hacen ensalada.");
        repository.daoPlato.insertar(plato2);

        Plato plato3 = new Plato();
        plato3.setPrecio(235.00);
        plato3.setTitulo("Milanesa con papas");
        plato3.setCalorias(560);
        plato3.setDescripcion("Milanesa de pollo acompañada de una porción de papas.");
        repository.daoPlato.insertar(plato3);

        Plato plato4 = new Plato();
        plato4.setPrecio(560.00);
        plato4.setTitulo("Bife");
        plato4.setCalorias(123);
        plato4.setDescripcion("Pedazo de carne.");
        repository.daoPlato.insertar(plato4);

        Plato plato5 = new Plato();
        plato5.setPrecio(132.45);
        plato5.setTitulo("Tortilla de papa");
        plato5.setCalorias(450);
        plato5.setDescripcion("Mecla de papa, huevo y cebolla.");
        repository.daoPlato.insertar(plato5);

        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);
        repository.daoUsuario.insertar(user);

        AppRepository.close();
    }
}
