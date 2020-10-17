package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.myapplication.PlateLogic.AdapterPlato;
import com.example.myapplication.dao.DaoPlato;
import com.example.myapplication.R;
import com.example.myapplication.model.Plato;

public class ActivityPlateRecycler extends AppCompatActivity {
    private DaoPlato daoPlato;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter plateAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*-- Indica que tipo de boton usar en las ViewHoldPlato,
            de acuerdo de si viene de la pantalla de ActivityPedido --*/
        Boolean addButtonAsk = false;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                addButtonAsk = false;
            } else {
                addButtonAsk = extras.getBoolean("addButtonAsk");
            }
        } else {
            addButtonAsk = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        daoPlato = new DaoPlato();
        plateAdapter = new AdapterPlato(this, daoPlato.list(), addButtonAsk);//,this);
        recyclerView.setAdapter(plateAdapter);

        Toolbar toolbar = findViewById(R.id.toolbarPlateRecycler);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Resources res = getResources();
        switch (item.getItemId()){
            case R.id.menuListOption1:
                Intent registerScreen = new Intent(this, ActivityPedido.class);
                startActivity(registerScreen);
                Log.d("New","ELIGIÓ NUEVO PEDIDO");

            default:
                //error desconocido
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    void setOnItemClickListener(AdapterView.OnItemClickListener listener)
void setOnItemLongClickListener(AdapterView.OnItemLongClickListener listener)
void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener)
     */
}