package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.PlateLogic.PlateRecyclerActivity;

import java.time.LocalDateTime;


public class PedidoActivity extends AppCompatActivity {

        private static final int REQUEST_CODE=222;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pedido);

            //Toolbar toolbar = findViewById(R.id.toolbarActivityHome);
            // setSupportActionBar(toolbar);


            final Button btnAddPlate = (Button) findViewById(R.id.btnAddPlate);

            btnAddPlate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(new Intent(getApplicationContext(), PlateRecyclerActivity.class),REQUEST_CODE);

                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                System.out.println("ok");
                }
            }else{
            Log.d("TagActivity", "fallo return de activityreslt");
            }
        }
}

