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
import SendMeal.app.R;
import app.database.AppRepository;
import app.database.OnResultCallback;
import app.model.Plato;

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
    private FirebaseAuth mAuth;
    private byte[] imageUploaded;
    private Plato newPlate;

    private void openCamera() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, CAMERA_REQUEST);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    private void deleteImage(){
        imageUpload.setImageDrawable(null);
        btnDeleteImage.setVisibility(View.INVISIBLE);
    }

    private void setIdPicture(){
        String s = Normalizer.normalize(plateName, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        ID_PICTURE = "plate-"+s.replace(" ", "-")+"-"+System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST:
                    Uri imageUri = data.getData();
                    try {
                        Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        imageUpload.setImageBitmap(bitmapImage);
                        setIdPicture();
                    } catch (IOException ignored) { }
                    break;
                case CAMERA_REQUEST:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    imageUpload.setImageBitmap(imageBitmap);
                    imageUploaded = baos.toByteArray(); // Imagen en arreglo de bytes
                    setIdPicture();
                    break;
            }
        }
    }

    private void uploadImage(){

        final StorageReference ref = FirebaseStorage.getInstance().getReference().child("images/"+ID_PICTURE);
        UploadTask uploadTask = ref.putBytes(imageUploaded);

            // Registramos un listener para saber el resultado de la operación
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continuamos con la tarea para obtener la URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        // URL de descarga del archivo
                        Uri downloadUri = task.getResult();
                        newPlate.setPhotoUrl(downloadUri.toString());
                        repository.insertarPlato(newPlate);
                    } else {
                        // Fallo
                    }
                }
            });
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

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_FROM_GALLERY_PERMISSION_CODE);
                else openGallery();
            }
        });

        //btnDeleteImage.setVisibility(View.INVISIBLE);
        btnDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteImage();
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
                    newPlate = new Plato();
                    newPlate.setTitle(titlePlate);
                    newPlate.setDescription(descriptionPlate);
                    newPlate.setPrice(Double.parseDouble(pricePlate));
                    newPlate.setCalories(Integer.parseInt(caloriesPlate));
                    uploadImage();
                    Toast.makeText(getApplicationContext(),R.string.ToastSuccessfulTransactionPlate,Toast.LENGTH_LONG).show();
                    finish();
                }
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
        Toast.makeText(ActivityNuevoPlato.this, "AsynTask exitosa!", Toast.LENGTH_SHORT).show();
    }
}