package com.example.myapplication.database;

import com.example.myapplication.model.Plato;

import java.util.ArrayList;
import java.util.List;

public class DAOPrueba {

    public DAOPrueba(){}

    public List<Plato> list(){
        //TODO implementar dao
        List<Plato> plateList = new ArrayList<Plato>();

        Plato plato1 = new Plato(), plato2 = new Plato(), plato3 = new Plato(), plato4 = new Plato(), plato5 = new Plato();
        plato1.setId(0);
        plato1.setPrice(199.99);
        plato1.setTitle("Hamburguesa completa");
        plato1.setCalories(349);
        plato1.setDescription("Hamburguesa de carne vacuna, con cebolla, queso chedar, aderezos y lechuga.");
        plateList.add(plato1);

        plato2.setId(1);
        plato2.setPrice(150.00);
        plato2.setTitle("Ensalada rusa");
        plato2.setCalories(96);
        plato2.setDescription("Un tipo de ensalada que tiene cosas que la hacen ensalada.");
        plateList.add(plato2);

        plato3.setId(2);
        plato3.setPrice(235.00);
        plato3.setTitle("Milanesa con papas");
        plato3.setCalories(560);
        plato3.setDescription("Milanesa de pollo acompañada de una porción de papas.");
        plateList.add(plato3);

        plato4.setId(3);
        plato4.setPrice(560.00);
        plato4.setTitle("Bife");
        plato4.setCalories(123);
        plato4.setDescription("Pedazo de carne.");
        plateList.add(plato4);

        plato5.setId(4);
        plato5.setPrice(132.45);
        plato5.setTitle("Tortilla de papa");
        plato5.setCalories(450);
        plato5.setDescription("Mecla de papa, huevo y cebolla.");
        plateList.add(plato5);


        return plateList;
    }
}
