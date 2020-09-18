package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbarActivityHome);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOption1:
                Intent pantallaRegistro = new Intent(this, MainActivity.class);
                startActivity(pantallaRegistro);
                Log.d("Tag1", "REGISTRARME");
                break;
            case R.id.menuOption2:
                Log.d("Tag2", "CREAR ITEM");
                break;
            case R.id.menuOption3:
                Log.d("Tag3","LISTA ITEMS");
                break;
            default:
                //error desconocido
        }
        return super.onOptionsItemSelected(item);
    }

}