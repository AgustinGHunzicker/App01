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

import java.util.ArrayList;
import java.util.List;

import app.adapters.PlatoRecycler;
import app.database.AppRepository;
import SendMeal.app.R;
import app.database.OnResultCallback;
import app.model.Plato;

public class ActivityPlatos extends AppCompatActivity implements OnResultCallback {

    RecyclerView recyclerView;
    AppRepository repository = null;

    boolean addButtonAsk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) addButtonAsk = extras.getBoolean("addButtonAsk");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_recycler);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlatoRecycler(this, new ArrayList<Plato>(), false));

        repository = AppRepository.getInstance(this,this);
        repository.buscarPlatos();

        Toolbar toolbar = findViewById(R.id.toolbarPlateRecycler);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_nuevo_pedido, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuListOption1) {
            Intent registerScreen = new Intent(this, ActivityPedido.class);
            startActivity(registerScreen);
            Log.d("New", "ELIGIÓ NUEVO PEDIDO");
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(List result) {
        recyclerView.setAdapter(new PlatoRecycler(this, (List<Plato>) result, addButtonAsk));
        //TODO seguir aca
    }
}