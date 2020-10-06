package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RegistrarUsuario extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
              EditText variables
         */
        final EditText editName = (EditText) findViewById(R.id.textUserName);
        final EditText editUserPassword = (EditText) findViewById(R.id.textUserPassword);
        final EditText editUserPasswordRepeat = (EditText) findViewById(R.id.textUserPasswordRepeat);
        final EditText editEmail = (EditText) findViewById(R.id.textEmailAddress);
        final EditText editCardNumber = (EditText) findViewById(R.id.textCardNumber);
        final EditText editCardCCV = (EditText) findViewById(R.id.textCardCCV);
        final EditText editCBU = (EditText) findViewById(R.id.textCBU);
        final EditText editAliasCBU = (EditText) findViewById(R.id.textAliasCBU);

        /*
            TextView variables
         */
        final TextView textViewAmountCredit = (TextView) findViewById(R.id.creditLoad);
        final TextView textInitialLoad = (TextView) findViewById(R.id.textInitialLoad);

        /*
            RadioButton variables
         */
        final RadioButton radioBtnDebit = (RadioButton) findViewById(R.id.radioButtonDebit);
        final RadioButton radioBtnCredit = (RadioButton) findViewById(R.id.radioButtonCredit);

        /*
            Spinner variables
         */
        final Spinner spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        final Spinner spinnerYear = (Spinner) findViewById(R.id.spinnerYear);

        /*
            Switch variables
         */
        final Switch switchInitLoad = (Switch) findViewById(R.id.switchInitialLoad);

        /*
            SeekBar variables
         */
        final SeekBar seekBarInitCredit = (SeekBar) findViewById(R.id.seekBarInitialCredit);

        /*
            CheckBox variables
         */
        final CheckBox checkBoxConditions = (CheckBox) findViewById(R.id.checkBoxConditions);

        /*
            Button variables
         */
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);


        /*----------------------------------------- Eventos---------------------------------------*/

        /*
            Capta el numero de tarjeta ingresado, cuando se completa se habilita el campo de
            codigo de seguridad CCV
         */
        editCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable numberCard) {
                if(numberCard.length() == 16)
                    editCardCCV.setEnabled(true);
                else {
                    editCardCCV.setText("");
                    editCardCCV.setEnabled(false);
                }
            }

        });

        /*
            Si se desplaza el switch de carga, habilita la seekBar para definir un
            monto de saldo de credito a cargar
         */
        switchInitLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchInitLoad.isChecked()) {
                    textInitialLoad.setVisibility(View.VISIBLE);
                    textViewAmountCredit.setVisibility(View.VISIBLE);
                    seekBarInitCredit.setVisibility(View.VISIBLE);
                } else {
                    textInitialLoad.setVisibility(View.GONE);
                    textViewAmountCredit.setVisibility(View.GONE);
                    seekBarInitCredit.setVisibility(View.GONE);
                }
            }
        });

        /*
            Refleja en el textViewAmountCredit el saldo que se va regulando con la seekBar
         */
        seekBarInitCredit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int amount, boolean b) {
                textViewAmountCredit.setText("$" + amount + "");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        /*
            Acepto los terminos y condiciones si se aceptaron, se habilit el boton de registrar
        */
        checkBoxConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.setEnabled(checkBoxConditions.isChecked());
            }
        });

        /*
            Load the years since this year + 12
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        Integer yearNow = localDateTime.getYear();
        ArrayList years = new ArrayList<>();
        years.add("");
        for(int i = 0; i < 12; i++){
            years.add(yearNow + i);
        }
        ArrayAdapter spinnerAdapterYears = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,years);
        spinnerYear.setAdapter(spinnerAdapterYears);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean invalid_space = false;
                String name = editName.getText().toString();
                String password = editUserPassword.getText().toString();
                String passwordCtn = editUserPasswordRepeat.getText().toString();
                String email = editEmail.getText().toString();
                String cbu = editCBU.getText().toString();
                String alias = editAliasCBU.getText().toString();
                String numberCard = editCardNumber.getText().toString();
                String numberCCV = editCardCCV.getText().toString();
                String selectedMonth = spinnerMonth.getSelectedItem().toString();
                String selectedYear = spinnerYear.getSelectedItem().toString();

                // + Adelante agregar campo en rojo en lo que este invalido
                if(name.equals(""))
                    invalid_space = true;
                //
                if(password.equals("") || !password.equals(passwordCtn))
                    invalid_space = true;
                //
                if(!email.contains("@"))
                    invalid_space = true;
                else if(email.substring(email.lastIndexOf("@")).length() < 3) invalid_space = true;
                //
                if(!radioBtnCredit.isChecked() && !radioBtnDebit.isChecked())
                    invalid_space = true;
                //
                if(numberCard.length() < 16)
                    invalid_space = true;
                //
                if(numberCCV.length() < 3)
                    invalid_space = true;
                //
                if(cbu.length() < 22)
                    invalid_space = true;
                //
                if(alias.length() < 6)
                    invalid_space = true;
                //
                if(!switchInitLoad.isChecked())
                    invalid_space = true;
                else if(seekBarInitCredit.getProgress()==0) invalid_space = true;
                //
                if(selectedMonth.equals("")||selectedYear.equals(""))
                    invalid_space = true;
                else{
                    LocalDateTime localDateTime = LocalDateTime.now();
                    Integer m = Integer.parseInt(selectedMonth);
                    Integer y = Integer.parseInt(selectedYear);
                    Integer mToday = localDateTime.getMonthValue();
                    Integer yToday = localDateTime.getYear();
                    if(y.equals(yToday) && mToday > m-3) invalid_space = true;
                }

                // Mensaje de datos invalidos
                if(invalid_space){
                    Toast.makeText(getApplicationContext(),R.string.ToastInvalidSpaces,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.ToastSuccessfulTransaction,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}