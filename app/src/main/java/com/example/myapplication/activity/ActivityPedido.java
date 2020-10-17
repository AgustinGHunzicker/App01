package com.example.myapplication.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.PlatoAdapter;
import com.example.myapplication.model.Plato;

import java.util.ArrayList;
import java.util.List;


public class ActivityPedido extends AppCompatActivity {

    private EditText textEmailAddress;
    private EditText textAddress;
    private RadioButton radioButtonEnvio;
    private RadioButton radioButtonTakeAway;
    private Button btnAddPlate;
    private Button btnConfirmarPedido;
    private TextView textTotalPrice;
    private ListView listPlates;
    private List<Plato> platosEnPedido;
    private PlatoAdapter plateAdapter;
    //Atributo que indica si se ha seleccionado un plato o no
    // private Boolean isPlateSelected = false;


    private static final int REQUEST_CODE=222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        BroadcastReceiver br = new MyFirstReceiver();

        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyFirstReceiver.OFERTA);
        getApplication().getApplicationContext().registerReceiver(br, filtro);

        Intent servicio = new Intent(this, taskSavePlate.class);
        servicio.putExtra("nombrePlato", "algo");
        startService(servicio);

        textEmailAddress = (EditText) findViewById(R.id.textEmailAddress);
        textAddress = (EditText) findViewById(R.id.textAddress);
        radioButtonEnvio = (RadioButton) findViewById(R.id.radioButtonEnvio);
        radioButtonTakeAway = (RadioButton) findViewById(R.id.radioButtonTakeAway);
        textTotalPrice = (TextView) findViewById(R.id.textTotalPrice);
        btnConfirmarPedido = (Button) findViewById(R.id.buttonAskPlate);
        btnAddPlate = (Button) findViewById(R.id.btnAddPlate);
        listPlates = (ListView) findViewById(R.id.listPlates);
        addListenerBtn();
        platosEnPedido = new ArrayList<Plato>();
        plateAdapter = new PlatoAdapter(this, platosEnPedido);
        listPlates.setAdapter(plateAdapter);
    }

    private void addListenerBtn(){
        btnAddPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityPlateRecycler.class);
                i.putExtra("addButtonAsk", true);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(platosEnPedido.size()>0){
                    Log.d("ASK", "Successful order");
                    Toast.makeText(getApplicationContext(),
                            R.string.successfulOrder,
                            Toast.LENGTH_LONG).show();
                    new taskSavePlate().execute("Succesfull");
                }
                else{
                    Log.d("ASK", "Failed order");
                    Toast.makeText(getApplicationContext(),
                            R.string.failedOrder,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if(requestCode == REQUEST_CODE) {

                Plato plato = (Plato) data.getSerializableExtra("plato");
                platosEnPedido.add(plato);
                //actualizar el adaptador
                plateAdapter = new PlatoAdapter(this, platosEnPedido);
                listPlates.setAdapter(plateAdapter);
            }

        }
        else
            Log.d("TagActivity", "fallo return de activity");
    }

    class taskSavePlate extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("5seg", "pasaron los 5 seg");
            Intent myIntention = new Intent();

            myIntention.setAction(MyFirstReceiver.OFERTA);
            myIntention.putExtra("Identificador", "On sale");
            sendBroadcast(myIntention);
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }

    }
}

