package app.activity;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.SphericalUtil;

import java.util.Random;

import SendMeal.app.R;

import static android.graphics.Color.RED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng puntoPedido;
    LatLng puntoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        actualizarMapa();
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.clear();
                puntoPedido = latLng;
                mMap.addMarker(new MarkerOptions().position(puntoPedido).title("Punto Pedido"));
                puntoLocal = generarPosicionLocal(puntoPedido);
                mMap.addMarker(new MarkerOptions().position(puntoLocal).title("Punto Local") );

                PolygonOptions polylineOptions = new PolygonOptions()
                        .add(puntoLocal).add(puntoPedido);
                Polygon p = mMap.addPolygon(polylineOptions);
                p.setStrokeColor(RED);
            }
        });
        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void actualizarMapa(){
        if (ActivityCompat.checkSelfPermission (this , Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    public LatLng generarPosicionLocal (LatLng posicionOriginal){
        Random r = new Random();

// Una direccion aleatoria de 0 a 359 grados
        int direccionRandomEnGrados = r.nextInt(360);

// Una distancia aleatoria de 100 a 1000 metros
        int distanciaMinima = 100;
        int distanciaMaxima = 1000;
        int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;

        LatLng nuevaPosicion = SphericalUtil.computeOffset(
                posicionOriginal,
                distanciaRandomEnMetros,
                direccionRandomEnGrados
        );

        return nuevaPosicion;
    }


}