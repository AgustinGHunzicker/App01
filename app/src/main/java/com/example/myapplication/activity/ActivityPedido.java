package com.example.myapplication.activity;

import android.app.Activity;
import android.app.AsyncNotedAppOp;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class ActivityPedido extends AppCompatActivity {

    EditText textEmailAddress;
    RadioButton radioButtonEnvio;
    RadioButton radioButtonTakeAway;
    EditText textAdress;
    Button editPrice;
    TextView textTitlePlate;
    TextView textPricePlate;
    Button askPlate;
    Boolean ask=false;


    private static final int REQUEST_CODE=222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        textEmailAddress = (EditText) findViewById(R.id.textEmailAddress);
        radioButtonEnvio = (RadioButton) findViewById(R.id.radioButtonEnvio);
        radioButtonTakeAway = (RadioButton) findViewById(R.id.radioButtonTakeAway);
        textAdress = (EditText) findViewById(R.id.textAdress);
        editPrice = (Button) findViewById(R.id.btnAddPlate);
        textTitlePlate = (TextView) findViewById(R.id.textTitlePlate);
        textPricePlate = (TextView) findViewById(R.id.textPricePlate);
        askPlate=(Button) findViewById(R.id.buttonAskPlate);


        BroadcastReceiver br = new MyFirstReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyFirstReceiver.OFERTA);
        getApplication().getApplicationContext().registerReceiver(br, filtro);

        Intent servicio = new Intent(this, taskSavePlate.class);
        servicio.putExtra("nombrePlato", "algo");
        startService(servicio);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        final Button btnAddPlate = (Button) findViewById(R.id.btnAddPlate);
        final Button btnAskPlate=(Button) findViewById(R.id.buttonAskPlate);

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
                };
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUEST_CODE) {
                //TODO cambiar por id, cuando se tenga la base de datos
                String producto = data.getExtras().getString("titlePlate");
                Double cantidad = data.getExtras().getDouble("pricePlate");


                //TODO agregar el producto a una lista, y la cantidad etc


                textTitlePlate = (TextView) findViewById(R.id.textTitlePlate);
                textPricePlate = (TextView) findViewById(R.id.textPricePlate);

                textTitlePlate.setText(getString(R.string.textTitlePlate)+producto);
                textPricePlate.setText(getString(R.string.textPricePlate)+cantidad.toString());
                ask=true;
            }
        }
        else{
            Log.d("TagActivity", "fallo return de activity");
        }
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

