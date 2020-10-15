package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        //Toolbar toolbar = findViewById(R.id.toolbarActivityHome);
        //setSupportActionBar(toolbar);

        final Button btnAddPlate = (Button) findViewById(R.id.btnAddPlate);

        btnAddPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityPlateRecycler.class);
                i.putExtra("addButtonAsk", true);
                startActivityForResult(i, REQUEST_CODE);
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
            }
        }
        else{
            Log.d("TagActivity", "fallo return de activity");
        }
    }
}

