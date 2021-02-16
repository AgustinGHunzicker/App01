package app.database;

import android.content.Context;

import app.model.Plato;
import app.model.Usuario;

public class DatosPrueba {

    public DatosPrueba (Context context){
        //AppDatabase db = AppDatabase.getInstance(context);//Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();

        AppRepository repository = AppRepository.getInstance(context, null);


        Plato plato1 = new Plato();
        plato1.setPrice(199.99);
        plato1.setTitle("Hamburguesa completa");
        plato1.setCalories(349);
        plato1.setDescription("Hamburguesa de carne vacuna, con cebolla, queso chedar, aderezos y lechuga.");
        repository.daoPlato.insertar(plato1);

        Plato plato2 = new Plato();
        plato2.setPrice(150.00);
        plato2.setTitle("Ensalada rusa");
        plato2.setCalories(96);
        plato2.setDescription("Un tipo de ensalada que tiene cosas que la hacen ensalada.");
        repository.daoPlato.insertar(plato2);

        Plato plato3 = new Plato();
        plato3.setPrice(235.00);
        plato3.setTitle("Milanesa con papas");
        plato3.setCalories(560);
        plato3.setDescription("Milanesa de pollo acompañada de una porción de papas.");
        repository.daoPlato.insertar(plato3);

        Plato plato4 = new Plato();
        plato4.setPrice(560.00);
        plato4.setTitle("Bife");
        plato4.setCalories(123);
        plato4.setDescription("Pedazo de carne.");
        repository.daoPlato.insertar(plato4);

        Plato plato5 = new Plato();
        plato5.setPrice(132.45);
        plato5.setTitle("Tortilla de papa");
        plato5.setCalories(450);
        plato5.setDescription("Mecla de papa, huevo y cebolla.");
        repository.daoPlato.insertar(plato5);

        Usuario user = new Usuario();
        user.setNombre("Usuario Prueba1");
        user.setEmail("usuarioprueba@mail.com");
        user.setClave("claveprueba");
        user.setCredito(1500d);
        repository.daoUsuario.insertar(user);
    }
}
