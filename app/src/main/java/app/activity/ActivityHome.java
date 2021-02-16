package app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import SendMeal.app.R;


public class ActivityHome extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                } else {
                    Toast.makeText(ActivityHome.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarActivityHome);
        setSupportActionBar(toolbar);

        //new DatosPrueba(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Resources res = getResources();
        switch (item.getItemId()){
            case R.id.menuOption1:
                Intent registerScreen = new Intent(this, ActivityRegistrarUsuario.class);
                startActivity(registerScreen);
                String registrar = res.getString(R.string.activityRegister);
                Log.d("Tag1", registrar);
                break;
            case R.id.menuOption2:
                Intent newPlateScreen = new Intent(this, ActivityNuevoPlato.class);
                startActivity(newPlateScreen);
                String createItem = res.getString(R.string.activityCreateItem);
                Log.d("Tag2", createItem);
                break;
            case R.id.menuOption3:
                Intent platesView = new Intent(this, ActivityPlatos.class);
                startActivity(platesView);
                String itemsList = res.getString(R.string.activityItemsList);
                Log.d("Tag3",itemsList);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}