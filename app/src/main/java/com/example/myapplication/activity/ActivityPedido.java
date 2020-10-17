package com.example.myapplication.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Plato;

import java.util.ArrayList;


public class ActivityPedido extends AppCompatActivity {

    private EditText textEmailAddress;
    private EditText textAddress;
    private RadioButton radioButtonEnvio;
    private RadioButton radioButtonTakeAway;
    private Button btnAddPlate;
    private Button btnAskPlate;
    private TextView textTotalPrice;
    private ListView listPlates;
    private ArrayAdapter<Plato> plateAdapter;

    private Boolean ask = false;


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
        btnAskPlate = (Button) findViewById(R.id.buttonAskPlate);
        btnAddPlate = (Button) findViewById(R.id.btnAddPlate);
        listPlates = (ListView) findViewById(R.id.listPlates);
        addListenerBtn();


        //Este arreglo lo hice para probar
        String platos[] = new String[]{};
        plateAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, platos);
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

        btnAskPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ask){
                    Log.d("ASK", "Successful order");
                    Toast.makeText(getApplicationContext(),R.string.successfulOrder,Toast.LENGTH_LONG).show();
                    new taskSavePlate().execute("Succesfull");
                }
                else{
                    Log.d("ASK", "Failed order");
                    Toast.makeText(getApplicationContext(),R.string.failedOrder,Toast.LENGTH_LONG).show();
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

                //TODO agregar platos a la lista
                /*
                Además, la actividad de creación de pedidos contará con un listado que mostrará
                el nombre de los platos incluidos y un detalle mostrando la cantidad de productos en la orden y el precio total.
                 */

                ask = true;
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

