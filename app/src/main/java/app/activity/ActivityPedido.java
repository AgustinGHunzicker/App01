package app.activity;

import android.annotation.SuppressLint;
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
import SendMeal.app.R;
import app.adapters.PlatoAdapter;
import app.model.Pedido;
import app.model.PedidoConPlatos;
import app.model.Plato;
import app.notifications.MyFirstReceiver;
import app.database.AppRepository;
import app.database.OnResultCallback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityPedido extends AppCompatActivity implements OnResultCallback {

    AppRepository repository = null;

    private EditText textEmailAddress;
    private EditText textAddress;
    private RadioButton radioButtonEnvio;
    private RadioButton radioButtonTakeAway;
    private Button btnAddPlate;
    private Button btnConfirmarPedido;
    private Button btnAddLocation;
    private TextView textTotalPrice;
    private TextView textCantidadProductos;
    private ListView listPlates;
    private List<Plato> platosEnPedido;
    private Double precioTotalPedido;
    private PlatoAdapter plateAdapter;

    private static final int REQUEST_CODE=222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        repository = AppRepository.getInstance(this.getApplicationContext(),this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        BroadcastReceiver br = new MyFirstReceiver();

        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyFirstReceiver.OFERTA);
        getApplication().getApplicationContext().registerReceiver(br, filtro);

        Intent servicio = new Intent(this, taskSavePlate.class);
        servicio.putExtra("nombrePlato", "algo");
        startService(servicio);

        textEmailAddress = findViewById(R.id.textEmailAddress);
        textAddress = findViewById(R.id.textAddress);
        radioButtonEnvio = findViewById(R.id.radioButtonEnvio);
        radioButtonTakeAway = findViewById(R.id.radioButtonTakeAway);
        textTotalPrice = findViewById(R.id.textTotalPrice);
        textCantidadProductos = findViewById(R.id.textCantidadProductos);
        btnConfirmarPedido = findViewById(R.id.buttonAskPlate);
        btnConfirmarPedido.setEnabled(false);
        btnAddPlate = findViewById(R.id.btnAddPlate);
        btnAddLocation = findViewById(R.id.buttonAddLocation);
        listPlates = findViewById(R.id.listPlates);
        precioTotalPedido = 0.0d;

        addListenerBtn();

        platosEnPedido = new ArrayList<>();

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

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(getApplicationContext(),MapsActivity.class);
              startActivity(i);
            }
        });

        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmailAddress.getText().toString();
                String address = textAddress.getText().toString();
                boolean invalid_space = false;

                if(!email.contains("@"))
                    invalid_space = true;
                else if(email.substring(email.lastIndexOf("@")).length() < 3) invalid_space = true;
                else if(address.length() < 1) invalid_space = true;
                else if(!radioButtonEnvio.isChecked() && !radioButtonTakeAway.isChecked()) invalid_space = true;

                if(invalid_space){
                    Log.d("ASK", "Failed order");
                    Toast.makeText(getApplicationContext(),
                            R.string.failedOrder,
                            Toast.LENGTH_LONG).show();
                }
                else{

                    Pedido pedido = new Pedido();
                    pedido.setCantidadPlatos(Integer.parseInt(textCantidadProductos.getText().toString()));
                    pedido.setDireccion(textAddress.getText().toString());
                    pedido.setEmail(textEmailAddress.getText().toString());
                    pedido.setPrice(Double.parseDouble(textTotalPrice.getText().toString().substring(2)));
                    if(radioButtonEnvio.isChecked()) pedido.setSeEnvia(true);
                    else pedido.setSeEnvia(false);
                    pedido.setFechaPedido(LocalDate.now());

                    //TODO ver si funciona esto
                    repository.insertarPedido(pedido, platosEnPedido);


                    Log.d("ASK", "Successful order");
                    Toast.makeText(getApplicationContext(),
                            R.string.successfulOrder,
                            Toast.LENGTH_LONG).show();
                    new taskSavePlate().execute("Succesfull");

                    Log.d("probando PEDIDO", pedido.toString());


                    textTotalPrice.setText("");
                    textCantidadProductos.setText("");
                    textAddress.setText("");
                    textEmailAddress.setText("");
                    radioButtonTakeAway.setChecked(false);
                    radioButtonEnvio.setChecked(false);
                    platosEnPedido.clear();
                    plateAdapter.clear();
                    listPlates.setAdapter(plateAdapter);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n") @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if(requestCode == REQUEST_CODE) {

                assert data != null;
                Plato plato = (Plato) data.getSerializableExtra("plato");
                plateAdapter.addPlato(plato);
                if(platosEnPedido.size() > 0) btnConfirmarPedido.setEnabled(true);

                precioTotalPedido += plato.getPrice();
                textTotalPrice.setText(" $"+String.format("%.2f", precioTotalPedido));

                textCantidadProductos.setText(""+platosEnPedido.size());
            }
        }
        else
            Log.d("TagActivity", "fallo return de SendMealApp.activity");
    }

    @Override
    public void onResult(List result) {

        Toast.makeText(ActivityPedido.this, "AsynTask exitosa!", Toast.LENGTH_SHORT).show();
    }

    class taskSavePlate extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() { }

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

