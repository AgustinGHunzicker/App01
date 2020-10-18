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
    private TextView textCantidadProductos;
    private ListView listPlates;
    private List<Plato> platosEnPedido;
    private PlatoAdapter plateAdapter;

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
        textCantidadProductos = (TextView) findViewById(R.id.textCantidadProductos);
        btnConfirmarPedido = (Button) findViewById(R.id.buttonAskPlate);
        btnConfirmarPedido.setEnabled(false);
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
                String email = textEmailAddress.getText().toString();
                String adress = textAddress.getText().toString();
                Boolean invalid_space = false;

                if(!email.contains("@"))
                    invalid_space = true;
                else if(email.substring(email.lastIndexOf("@")).length() < 3) invalid_space = true;
                else if(adress.length() < 1) invalid_space = true;
                else if(!radioButtonEnvio.isChecked() && !radioButtonTakeAway.isChecked()) invalid_space = true;

                if(invalid_space){
                    Log.d("ASK", "Failed order");
                    Toast.makeText(getApplicationContext(),
                            R.string.failedOrder,
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Log.d("ASK", "Successful order");
                    Toast.makeText(getApplicationContext(),
                            R.string.successfulOrder,
                            Toast.LENGTH_LONG).show();
                    new taskSavePlate().execute("Succesfull");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if(requestCode == REQUEST_CODE) {

                assert data != null;
                Plato plato = (Plato) data.getSerializableExtra("plato");
                platosEnPedido.add(plato);
                if(platosEnPedido.size() > 0) btnConfirmarPedido.setEnabled(true);
                //actualizar el adaptador
                plateAdapter = new PlatoAdapter(this, platosEnPedido);
                listPlates.setAdapter(plateAdapter);
                Double precioTotalPedido = (double) 0;
                //Calculo el precio total del pedido sumando precios unitarios
                int i = 0;
                for(; i < platosEnPedido.size(); i++)
                    precioTotalPedido += platosEnPedido.get(i).getPrice();
                //seteo el precio total en el textView
                textTotalPrice.setText(" $"+precioTotalPedido.toString());
                //
                textCantidadProductos.setText(""+i);
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

