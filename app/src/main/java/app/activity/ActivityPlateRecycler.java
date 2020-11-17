package app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import app.adapters.PlatoRecyclerAdapter;
import app.database.AppRepository;
import app.database.DatosPrueba;
import SendMeal.app.R;
import app.database.OnResultCallback;
import app.model.Plato;

public class ActivityPlateRecycler extends AppCompatActivity implements OnResultCallback {
    DatosPrueba daoPrueba;
    RecyclerView recyclerView;
    RecyclerView.Adapter plateAdapter;
    RecyclerView.LayoutManager layoutManager;
    AppRepository repository = null;
    boolean addButtonAsk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*-- Indica que tipo de boton usar en las ViewHoldPlato,
            de acuerdo de si viene de la pantalla de ActivityPedido --*/

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

        new DatosPrueba(this.getApplicationContext());
        repository = AppRepository.getInstance(this.getApplicationContext(),this);
        repository.buscarPlatos();


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

    @Override
    public void onResult(List result) {

        for(Object a:result){
            Log.d("plato", ((Plato)a).getTitle());
        }

        //TODO seguir aca
        Toast.makeText(ActivityPlateRecycler.this, "AsynTask exitosa!", Toast.LENGTH_SHORT).show();
        plateAdapter = new PlatoRecyclerAdapter(this, (List<Plato>) result, addButtonAsk);//,this);
        recyclerView.setAdapter(plateAdapter);
    }
}