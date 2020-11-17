package app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import SendMeal.app.R;
import app.database.AppRepository;
import app.database.OnResultCallback;
import app.model.Plato;

import java.util.List;

public class ActivityNuevoPlato extends AppCompatActivity implements OnResultCallback {

    AppRepository repository = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        /* EditText variables */
        final EditText editTitle = findViewById(R.id.textTitlePlate);
        final EditText editDescription =  findViewById(R.id.textDescriptionPlate);
        final EditText editPrice = findViewById(R.id.textPricePlate);
        final EditText editCalories =  findViewById(R.id.textCaloriesPlate);

        /* Button variables */
        final Button btnSave = findViewById(R.id.btnSave);

        repository = AppRepository.getInstance(this.getApplicationContext(),this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean invalid_space = false;
                String titlePlate = editTitle.getText().toString();
                String descriptionPlate = editDescription.getText().toString();
                String pricePlate = editPrice.getText().toString();
                String caloriesPlate = editCalories.getText().toString();

                if(titlePlate.equals("")) invalid_space = true;
                if(descriptionPlate.equals("")) invalid_space = true;
                if(pricePlate.equals("")) invalid_space = true;
                else  if(Double.parseDouble(pricePlate) <= 0) invalid_space = true;
                if(caloriesPlate.equals("")) invalid_space = true;
                else if(Integer.parseInt(caloriesPlate) <= 0) invalid_space = true;

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

                    repository.insertarPlato(newPlate);
                    Toast.makeText(getApplicationContext(),R.string.ToastSuccessfulTransactionPlate,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onResult(List result) {
        Toast.makeText(ActivityNuevoPlato.this, "AsynTask exitosa!", Toast.LENGTH_SHORT).show();
    }
}