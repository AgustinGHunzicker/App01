package com.example.myapplication.PlateLogic;

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
import android.widget.Toast;

import com.example.myapplication.R;

public class PlateRecyclerActivity extends AppCompatActivity {
    private PlatesDao daoPlates;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter plateAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_recycler);
        daoPlates = new PlatesDao();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        plateAdapter = new PlateAdapter(daoPlates.list(), listenerAskButton);//,this);

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
                Intent registerScreen = new Intent(this, PedidoActivity.class);
                startActivity(registerScreen);
                Log.d("New","ELIGIÓ NUEVO PEDIDO");

            default:
                //error desconocido
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener listenerAskButton = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent intentResultado = new Intent();
                    intentResultado.putExtra("cantidad",
                            Integer.valueOf(
                                    edtCantidad.getText().toString())
                    );
                    intentResultado.putExtra("producto",eleccion);
                    setResult(Activity.RESULT_OK, intentResultado);
                    */
                    Toast.makeText(getApplicationContext(),"tu viej",Toast.LENGTH_LONG).show();
                    finish();
                }
    };

}