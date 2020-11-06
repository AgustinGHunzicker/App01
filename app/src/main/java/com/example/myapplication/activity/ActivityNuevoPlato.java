package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.model.Plato;

public class ActivityNuevoPlato extends AppCompatActivity {
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        /*
              EditText variables
         */
        final EditText editTitle = (EditText) findViewById(R.id.textTitlePlate);
        final EditText editDescription = (EditText) findViewById(R.id.textDescriptionPlate);
        final EditText editPrice = (EditText) findViewById(R.id.textPricePlate);
        final EditText editCalories = (EditText) findViewById(R.id.textCaloriesPlate);

        /*
            Button variables
         */
        final Button btnSave = (Button) findViewById(R.id.btnSave);


        //Instancia de la Bd
        db = Room.databaseBuilder(getApplicationContext(),
         AppDataBase.class, "midb").allowMainThreadQueries().build();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean invalid_space = false;
                String titlePlate = editTitle.getText().toString();
                String descriptionPlate = editDescription.getText().toString();
                String pricePlate = editPrice.getText().toString();
                String caloriesPlate = editCalories.getText().toString();

                // TODO Adelante agregar campo en rojo en lo que este invalido
                if(titlePlate.equals(""))
                    invalid_space = true;
                //
                if(descriptionPlate.equals(""))
                    invalid_space = true;
                //
                if(pricePlate.equals(""))
                    invalid_space = true;
                else{
                    if(Double.parseDouble(pricePlate) <= 0)
                        invalid_space = true;
                }
                //
                if(caloriesPlate.equals(""))
                    invalid_space = true;
                else{
                    if(Integer.parseInt(caloriesPlate) <= 0)
                        invalid_space = true;
                }

                // Mensaje de datos invalidos
                if(invalid_space){
                    Toast.makeText(getApplicationContext(),R.string.ToastInvalidSpacesPlate,Toast.LENGTH_LONG).show();
                }
                else{
                    Plato newPlate = new Plato();
                    newPlate.setTitle(titlePlate);
                    newPlate.setDescription(descriptionPlate);
                    newPlate.setPrice(Double.parseDouble(pricePlate));
                    newPlate.setCalories(Integer.parseInt(caloriesPlate));
                    Toast.makeText(getApplicationContext(),R.string.ToastSuccessfulTransactionPlate,Toast.LENGTH_LONG).show();

                    //Insertar en la bd Dao

                    long result = db.platoDao().insertar(newPlate);
                    if(result>0){
                        //correcto
                        Log.d("DB", "Se guardo en la DB");

                    }else{
                        //no se inserto correctamente
                        Log.d("DB", "No se guardo en la DB");
                    }

                }
            }
        });
    }


}