package app.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;

import SendMeal.app.R;
import app.database.AppRepository;
import app.database.OnResultCallback;
import app.model.Plato;

public class ActivityNuevoPlato extends AppCompatActivity implements OnResultCallback {

    Button btnGuardar;
    EditText textTitulo;
    EditText textDescripcion;
    EditText textPrecio;
    EditText textCalorias;
    ImageButton btnCamara;
    ImageButton btnBorrarImagen;
    ImageButton btnGalleria;
    ImageView imagenPlato;

    private AppRepository repository = null;

    private static final int CAMARA_REQUEST = 1;
    private static final int GALLERIA_REQUEST = 2;
    private static final int PERMISSION_CODE_MY_CAMERA = 100;
    private static final int PICK_FROM_GALLERY_PERMISSION_CODE = 101;

    private String ID_PICTURE;
    private byte[] imagenSubida;
    private Plato nuevoPlato;

    private void openCamera() { startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMARA_REQUEST); }
    private void openGallery() { startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GALLERIA_REQUEST); }

    private void deleteImage(){
        imagenPlato.setImageDrawable(null);
        btnBorrarImagen.setVisibility(View.INVISIBLE);
    }

    private void setIdPicture(){
        String s = Normalizer.normalize(textTitulo.getText(), Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        ID_PICTURE = "plate-"+s.replace(" ", "-")+"-"+System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERIA_REQUEST:
                    Uri imageUri = data.getData();
                    try {
                        Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        imagenPlato.setImageBitmap(imageBitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        imagenPlato.setImageBitmap(imageBitmap);
                        imagenSubida = baos.toByteArray();
                    } catch (IOException ignored) { }
                    break;
                case CAMARA_REQUEST:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    imagenPlato.setImageBitmap(imageBitmap);
                    imagenSubida = baos.toByteArray();
                    break;
            }
        }
    }

    private void uploadImage(){
        setIdPicture();
        if(imagenSubida != null){
            final StorageReference ref = FirebaseStorage.getInstance().getReference().child("images/"+ID_PICTURE);
            UploadTask uploadTask = ref.putBytes(imagenSubida);

            uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }
                return ref.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    nuevoPlato.setFotoUrl(downloadUri.toString());
                    repository.insertarPlato(nuevoPlato);
                    Toast.makeText(getApplicationContext(), R.string.ToastSuccessfulTransactionPlate, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.ToastErrorTransactionPlate, Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            repository.insertarPlato(nuevoPlato);
            Toast.makeText(getApplicationContext(), R.string.ToastSuccessfulTransactionPlate, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);
        FirebaseAuth.getInstance().signInAnonymously();

        repository = new AppRepository(this,this);

        btnGuardar = findViewById(R.id.btnSave);
        btnCamara = findViewById(R.id.btnCamara);
        btnBorrarImagen = findViewById(R.id.btnBorrarImagen);
        btnGalleria = findViewById(R.id.btnGalleria);
        textTitulo = findViewById(R.id.textTituloPlato);
        textDescripcion =  findViewById(R.id.textDescripcionPlato);
        textPrecio = findViewById(R.id.textPrecioPlato);
        textCalorias =  findViewById(R.id.textCaloriasPlato);
        imagenPlato = findViewById(R.id.imagenPlato);

        btnCamara.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE_MY_CAMERA);
            else openCamera();
        });

        btnGalleria.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_FROM_GALLERY_PERMISSION_CODE);
            else openGallery();
        });

        //btnDeleteImage.setVisibility(View.INVISIBLE);
        btnBorrarImagen.setOnClickListener(v -> deleteImage());

        btnGuardar.setOnClickListener(view -> {

            Boolean invalid_space = false;
            String titlePlate = textTitulo.getText().toString();
            String descriptionPlate = textDescripcion.getText().toString();
            String pricePlate = textPrecio.getText().toString();
            String caloriesPlate = textCalorias.getText().toString();

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
                nuevoPlato = new Plato();
                nuevoPlato.setTitulo(titlePlate);
                nuevoPlato.setDescripcion(descriptionPlate);
                nuevoPlato.setPrecio(Double.parseDouble(pricePlate));
                nuevoPlato.setCalorias(Integer.parseInt(caloriesPlate));
                uploadImage();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE_MY_CAMERA:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) openCamera();
                    else Toast.makeText(this, "Error al acceder a la camara", Toast.LENGTH_SHORT).show();
                break;
            case PICK_FROM_GALLERY_PERMISSION_CODE:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) openGallery();
                    else Toast.makeText(this, "Error al acceder a la galeria", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onResult(List result) {
        Toast.makeText(this, R.string.ToastSuccessfulTransactionPlate, Toast.LENGTH_LONG).show();
        finish();
    }
}