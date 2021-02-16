package app.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;

import SendMeal.app.R;
import app.database.AppRepository;
import app.database.OnResultCallback;
import app.extras.ImageHelper;
import app.model.Plato;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

public class ActivityNuevoPlato extends AppCompatActivity implements OnResultCallback {

    AppRepository repository = null;
    protected ImageButton btnCamera;
    protected ImageButton btnDeleteImage;
    protected ImageButton btnGallery;
    protected ImageView imageUpload;
    protected Button btnSave;
    protected String plateName;
    protected String idPlate;
    private static final int CAMERA_REQUEST = 1;
    private static final int GALLERY_REQUEST = 2;
    private static final int PERMISSION_CODE_MY_CAMERA = 100;
    private static final int PICK_FROM_GALLERY_PERMISSION_CODE = 101;
    private String ID_PICTURE;
    private String IMAGE_PATH_CACHE;
    private String IMAGE_PATH_FINAL;
    private FirebaseAuth mAuth;

    private void openCamera() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, CAMERA_REQUEST);
    }


    private void deleteImage(){
        imageUpload.setImageDrawable(null);
        btnDeleteImage.setVisibility(View.INVISIBLE);
    }


    private void setIdPicture(){
        String s = Normalizer.normalize(plateName, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        ID_PICTURE = "field-"+s.replace(" ", "-")+"-"+System.currentTimeMillis();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == CAMERA_REQUEST ||  requestCode == GALLERY_REQUEST) && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray(); // Imagen en arreglo de bytes
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
        plateName = "id-plate";

        /* EditText variables */
        final EditText editTitle = findViewById(R.id.textTitlePlate);
        final EditText editDescription =  findViewById(R.id.textDescriptionPlate);
        final EditText editPrice = findViewById(R.id.textPricePlate);
        final EditText editCalories =  findViewById(R.id.textCaloriesPlate);

        /* Button variables */
        btnSave = findViewById(R.id.btnSave);
        repository = AppRepository.getInstance(this.getApplicationContext(),this);
        idPlate = getIntent().getStringExtra("idPlate");

        setIdPicture();

        btnCamera = findViewById(R.id.btnCamera);
        btnDeleteImage = findViewById(R.id.btnDeleteImage);
        btnGallery = findViewById(R.id.btnGallery);
        imageUpload = findViewById(R.id.imageUpload);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE_MY_CAMERA);
                else openCamera();
            }
        });

        btnDeleteImage.setVisibility(View.INVISIBLE);
        btnDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNuevoPlato.this.deleteImage();
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE_MY_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) openCamera();
            else Toast.makeText(this, "Error al acceder a la camara", Toast.LENGTH_SHORT).show();
        }
    }

    public void onResult(List result) {
        Toast.makeText(ActivityNuevoPlato.this, "AsynTask exitosa!", Toast.LENGTH_SHORT).show();
    }
}