package com.example.app_turistica_android.Maps;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.app_turistica_android.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnTypeSatelite, btnTypeHybrid;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

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
        miUbicacion();

        Localizaciones(googleMap);
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(miUbicacion);

    }

    private void actUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000,0,locListener);
    }


    public void Localizaciones(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng Retiro = new LatLng( 40.415427, -3.684490 );
        mMap.addMarker(new MarkerOptions().position(Retiro).title("Parque del Retiro"));
        final LatLng Cibeles = new LatLng(40.419397, -3.693186);
        mMap.addMarker(new MarkerOptions().position(Cibeles).title("Fuente de Cibeles"));
        final LatLng Neptuno = new LatLng( 40.415226, -3.694086 );
        mMap.addMarker(new MarkerOptions().position(Neptuno).title("Fuente de Neptuno"));
        final LatLng MPrado = new LatLng( 40.414072, -3.692334 );
        mMap.addMarker(new MarkerOptions().position(MPrado).title("Museo del Prado"));
        final LatLng KM0 = new LatLng( 40.416671, -3.703817 );
        mMap.addMarker(new MarkerOptions().position(KM0).title("Sol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(KM0));//Señalar punto carga del mapa
        final LatLng PlzMayor = new LatLng( 40.415560, -3.707426 );
        mMap.addMarker(new MarkerOptions().position(PlzMayor).title("Plaza Mayor"));
        final LatLng PalaReal = new LatLng( 40.418134, -3.714430 );
        mMap.addMarker(new MarkerOptions().position(PalaReal).title("Palacio Real Madrid"));
        final LatLng TDebod = new LatLng( 40.424075, -3.717688 );
        mMap.addMarker(new MarkerOptions().position(TDebod).title("Templo de Debod"));
        final LatLng PAlcala = new LatLng( 40.420007, -3.688766 );
        mMap.addMarker(new MarkerOptions().position(PAlcala).title("Puerta de Alcalá"));
        final LatLng PAtracc = new LatLng( 40.411917, -3.750087 );
        mMap.addMarker(new MarkerOptions().position(PAtracc).title("Parque de Atracciones"));


    }
}
