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
import app.model.Plato;
import app.notifications.MyFirstReceiver;
import app.database.AppRepository;
import app.database.OnResultCallback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityPedido extends AppCompatActivity implements OnResultCallback {
    EditText textEmail;
    EditText textDireccion;
    RadioButton rbEnvio;
    RadioButton rbParaLLevar;
    Button btnAddPlato;
    Button btnConfirmarPedido;
    Button btnAddUbicacion;
    TextView textPrecioTotal;
    TextView textCantidadProductos;

    private AppCompatActivity _CONTEXT;
    private AppRepository repository = null;

    private ListView listaPlatos;
    private List<Plato> platosEnPedido;
    private Double precioTotalPedido;

    private static final int REQUEST_CODE=222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        _CONTEXT = this;

        repository = new AppRepository(this,this);

        textEmail = findViewById(R.id.textEmail);
        textDireccion = findViewById(R.id.textDireccion);
        rbEnvio = findViewById(R.id.rbEnvio);
        rbParaLLevar = findViewById(R.id.rbParaLLevar);
        textPrecioTotal = findViewById(R.id.textPrecioTotal);
        textCantidadProductos = findViewById(R.id.textCantidadProductos);
        btnConfirmarPedido = findViewById(R.id.btnConfirmarPedido);
        btnConfirmarPedido.setEnabled(false);
        btnAddPlato = findViewById(R.id.btnAddPlato);
        btnAddUbicacion = findViewById(R.id.btnAddUbicacion);
        listaPlatos = findViewById(R.id.listaPlatos);

        //TODO quitar
        precioTotalPedido = 0.0d;
        textEmail.setText("fulanito@gmail.com");
        textDireccion.setText("direccio n 999");

        BroadcastReceiver br = new MyFirstReceiver();

        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyFirstReceiver.OFERTA);
        getApplication().getApplicationContext().registerReceiver(br, filtro);

        Intent servicio = new Intent(this, taskGuardarPlato.class);
        servicio.putExtra("nombrePlato", "algo");
        startService(servicio);

        addListenerBtn();

        platosEnPedido = new ArrayList<>();
        listaPlatos.setAdapter(new PlatoAdapter(this, platosEnPedido));
    }


    private void addListenerBtn(){
        btnAddPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityPlatos.class);
                i.putExtra("addButtonAsk", true);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        btnAddUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(getApplicationContext(), ActivityMap.class);
              startActivity(i);
            }
        });

        btnConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString();
                String address = textDireccion.getText().toString();
                boolean invalid_space = false;

                if(!email.contains("@")) invalid_space = true;
                else if(email.substring(email.lastIndexOf("@")).length() < 3) invalid_space = true;
                else if(address.length() < 1) invalid_space = true;
                else if(!rbEnvio.isChecked() && !rbParaLLevar.isChecked()) invalid_space = true;

                if(invalid_space){
                    Log.d("ASK", "Failed order");
                    Toast.makeText(getApplicationContext(), R.string.failedOrder, Toast.LENGTH_LONG).show();
                }
                else{
                    Pedido pedido = new Pedido();
                    pedido.setCantidadPlatos(Integer.parseInt(textCantidadProductos.getText().toString()));
                    pedido.setDireccion(textDireccion.getText().toString());
                    pedido.setEmail(textEmail.getText().toString());
                    pedido.setPrecio(Double.parseDouble(textPrecioTotal.getText().toString().substring(2)));
                    pedido.setSeEnvia(rbEnvio.isChecked());
                    pedido.setFechaPedido(LocalDate.now());

                    //TODO ver si funciona esto
                    repository.insertarPedido(pedido, platosEnPedido);


                    Log.d("ASK", "Successful order");
                    Toast.makeText(getApplicationContext(), R.string.successfulOrder, Toast.LENGTH_LONG).show();
                    new taskGuardarPlato().execute("Succesfull");

                    Log.d("probando PEDIDO", pedido.toString());

                    startActivity(new Intent(_CONTEXT, ActivityHome.class));
                    finishAffinity();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n") @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            assert data != null;
            Plato plato = (Plato) data.getSerializableExtra("plato");
            platosEnPedido.add(plato);
            listaPlatos.setAdapter(new PlatoAdapter(ActivityPedido.this, platosEnPedido));

            if(platosEnPedido.size() > 0) btnConfirmarPedido.setEnabled(true);

            precioTotalPedido += plato.getPrecio();
            textPrecioTotal.setText(" $"+String.format("%.2f", precioTotalPedido));

            textCantidadProductos.setText(""+platosEnPedido.size());
        }
        else
            Log.d("TagActivity", "fallo return de SendMealApp.activity");
    }

    @Override
    public void onResult(List result) {
        Toast.makeText(ActivityPedido.this, "AsynTask exitosa!", Toast.LENGTH_SHORT).show();
    }

    class taskGuardarPlato extends AsyncTask<String, Void, String> {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppRepository.close();
    }
}

