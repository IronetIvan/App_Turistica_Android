package com.example.app_turistica_android.Maps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.app_turistica_android.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnTypeSatelite, btnTypeHybrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnTypeSatelite = (Button) findViewById(R.id.btnSatelite);
        btnTypeHybrid = (Button) findViewById(R.id.btnHybrid);
        btnTypeSatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        btnTypeHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
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

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sidney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng madrid = new LatLng(40.41,-3.69);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Madrid"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));*/
        Localizaciones(googleMap);
    }

    public void Localizaciones(GoogleMap googleMap){
        mMap = googleMap;

        final LatLng Retiro = new LatLng(40.4147864,-3.687761);
        mMap.addMarker(new MarkerOptions().position(Retiro).title("Parque del Retiro"));
        final LatLng Cibeles = new LatLng(40.4193367,-3.6952712);
        mMap.addMarker(new MarkerOptions().position(Cibeles).title("Fuente de Cibeles"));
        final LatLng Neptuno = new LatLng(40.4152084,-3.6962812);
        mMap.addMarker(new MarkerOptions().position(Neptuno).title("Fuente de Neptuno"));
        final LatLng MPrado = new LatLng(40.4137859,-3.6943158);
        mMap.addMarker(new MarkerOptions().position(MPrado).title("Museo del Prado"));
        final LatLng KM0 = new LatLng(40.4166373,-3.7045839);
        mMap.addMarker(new MarkerOptions().position(KM0).title("Sol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(KM0));//Señalar punto carga del mapa
        final LatLng PlzMayor = new LatLng(40.4153774,-3.7082803);
        mMap.addMarker(new MarkerOptions().position(PlzMayor).title("Plaza Mayor"));
        final LatLng PalaReal = new LatLng(40.4179591,-3.7165007);
        mMap.addMarker(new MarkerOptions().position(PalaReal).title("Palacio Real Madrid"));
        final LatLng TDebod = new LatLng(40.4240257,-3.7199582);
        mMap.addMarker(new MarkerOptions().position(TDebod).title("Templo de Debod"));




    }
}
