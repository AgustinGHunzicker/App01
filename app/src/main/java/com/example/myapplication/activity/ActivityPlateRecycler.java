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

import com.example.myapplication.PlateLogic.AdapterPlato;
import com.example.myapplication.dao.DaoPlato;
import com.example.myapplication.R;

public class ActivityPlateRecycler extends AppCompatActivity {
    private DaoPlato daoPlato;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter plateAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        daoPlato = new DaoPlato();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        plateAdapter = new AdapterPlato(this, daoPlato.list(), addButtonAsk);//,this);

        recyclerView.setAdapter(plateAdapter);

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

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

    /**
     * Función que es llamada desde el listener de AdapterPlato, luego de elegir un plato para agregar al pedido
     * @param titlePlate nombre del plaro
     * @param pricePlate precio del platp
     */
    public void back(String titlePlate, Double pricePlate) {
        Intent i = new Intent();
        i.putExtra("titlePlate", titlePlate);
        i.putExtra("pricePlate", pricePlate);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}