package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.adapters.PlatoRecyclerAdapter;
import com.example.myapplication.pruebaBaseDatos.DAOPrueba;
import com.example.myapplication.R;

public class ActivityPlateRecycler extends AppCompatActivity {
    DAOPrueba daoPrueba;
    RecyclerView recyclerView;
    RecyclerView.Adapter plateAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*-- Indica que tipo de boton usar en las ViewHoldPlato,
            de acuerdo de si viene de la pantalla de ActivityPedido --*/
        boolean addButtonAsk = false;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null)
                addButtonAsk = extras.getBoolean("addButtonAsk");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_recycler);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        daoPrueba = new DAOPrueba();
        plateAdapter = new PlatoRecyclerAdapter(this, daoPrueba.list(), addButtonAsk);//,this);
        recyclerView.setAdapter(plateAdapter);

        Toolbar toolbar = findViewById(R.id.toolbarPlateRecycler);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuListOption1) {
            Intent registerScreen = new Intent(this, ActivityPedido.class);
            startActivity(registerScreen);
            Log.d("New", "ELIGIÓ NUEVO PEDIDO");
        } // else error desconocido
        return super.onOptionsItemSelected(item);
    }

}