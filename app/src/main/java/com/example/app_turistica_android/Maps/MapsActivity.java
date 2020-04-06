package com.example.app_turistica_android.Maps;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.app_turistica_android.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.Provider;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnTypeSatelite, btnTypeHybrid;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

    BottomNavigationView botonNavegacion;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        instancias();
        acciones();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void acciones() {
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

        botonNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId() == R.id.mapas) {
                    Toast.makeText(MapsActivity.this, "Muestra el Fragment con el mapa", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.lugares) {
                    Toast.makeText(MapsActivity.this, "Muestra un listado con los lugares que sube la gente", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.addruta) {
                    Toast.makeText(MapsActivity.this, "Pantalla para agregar una ruta", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.perfilusuario) {
                    Toast.makeText(MapsActivity.this, "Listado del usuario con opciones como (nombre de usuario, rutas, favoritos, cambiar email/password", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });
    }

    private void instancias() {
        btnTypeSatelite = findViewById(R.id.btnSatelite);
        btnTypeHybrid = findViewById(R.id.btnHybrid);
        botonNavegacion = findViewById(R.id.navegacion);

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_superior);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Localizaciones(googleMap);
        final LatLng KM0 = new LatLng(40.416671, -3.703817);
        mMap.addMarker(new MarkerOptions().position(KM0).title("Sol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(KM0));//Señalar punto carga del mapa
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(KM0, 18), 5000, null);//Zoom en un punto a la hora de cargar

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() { //Creamos marcas FAV del usuario Click largo
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.recommended)).anchor(0.0f, 1.0f).position(latLng));
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { //hacer click en ubicación del mapa
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "Hemos pulsado una ubicación", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        miUbicacion();

    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi ubicación").icon(BitmapDescriptorFactory.fromResource(R.drawable.adduser)));
        mMap.animateCamera(miUbicacion);

    }

    private void actUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
        Log.v("mapa", "localizacion ready");
        Toast.makeText(getApplicationContext(), String.format("Mi ubicacion es %.2f %.2f", lat, lng), Toast.LENGTH_SHORT).show();
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actUbicacion(location);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
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

    private void miUbicacion2() {
        if (hayPermisoLocalizacion()) {

        } else {

            String[] permisos = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(MapsActivity.this,
                    permisos, 1);

        }
        Log.v("mapa", String.valueOf(hayPermisoLocalizacion()));
    }

    private void miUbicacion() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permisos = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(MapsActivity.this,
                    permisos, 1);
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000,0,locListener);
    }

    public boolean hayPermisoLocalizacion() {
        return (ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }


    public void Localizaciones(GoogleMap googleMap) {
        mMap = googleMap;

        // TODO: estos datos tenéis que traerlos de firebase, que son los favoritos del usuarios
        final LatLng Retiro = new LatLng(40.415427, -3.684490);
        mMap.addMarker(new MarkerOptions().position(Retiro).title("Parque del Retiro"));
        final LatLng Cibeles = new LatLng(40.419397, -3.693186);
        mMap.addMarker(new MarkerOptions().position(Cibeles).title("Fuente de Cibeles"));
        final LatLng Neptuno = new LatLng(40.415226, -3.694086);
        mMap.addMarker(new MarkerOptions().position(Neptuno).title("Fuente de Neptuno"));
        final LatLng MPrado = new LatLng(40.414072, -3.692334);
        mMap.addMarker(new MarkerOptions().position(MPrado).title("Museo del Prado"));
        final LatLng PlzMayor = new LatLng(40.415560, -3.707426);
        mMap.addMarker(new MarkerOptions().position(PlzMayor).title("Plaza Mayor"));
        final LatLng PalaReal = new LatLng(40.418134, -3.714430);
        mMap.addMarker(new MarkerOptions().position(PalaReal).title("Palacio Real Madrid"));
        final LatLng TDebod = new LatLng(40.424075, -3.717688);
        mMap.addMarker(new MarkerOptions().position(TDebod).title("Templo de Debod"));
        final LatLng PAlcala = new LatLng(40.420007, -3.688766);
        mMap.addMarker(new MarkerOptions().position(PAlcala).title("Puerta de Alcalá"));
        final LatLng PAtracc = new LatLng(40.411917, -3.750087);
        mMap.addMarker(new MarkerOptions().position(PAtracc).title("Parque de Atracciones"));
        final LatLng CBellasArtes = new LatLng(40.418336, -3.696545);
        mMap.addMarker(new MarkerOptions().position(CBellasArtes).title("Círculo de Bellas Artes"));
        final LatLng MReianSofia = new LatLng(40.407998, -3.694515);
        mMap.addMarker(new MarkerOptions().position(MReianSofia).title("Museo Reina Sofia"));
        final LatLng mTyssen = new LatLng(40.416026, -3.694930);
        mMap.addMarker(new MarkerOptions().position(mTyssen).title("Museo Thyssen-Bornemisza"));
        final LatLng GranVia = new LatLng(40.420278, -3.705495);
        mMap.addMarker(new MarkerOptions().position(GranVia).title("Gran Vía"));
        final LatLng PlzEspana = new LatLng(40.423402, -3.712172);
        mMap.addMarker(new MarkerOptions().position(PlzEspana).title("Plaza de España"));
        final LatLng Lavapies = new LatLng(40.408866, -3.701124);
        mMap.addMarker(new MarkerOptions().position(Lavapies).title("Barrio de Lavapies"));
        final LatLng Matadero = new LatLng(40.391633, -3.697514);
        mMap.addMarker(new MarkerOptions().position(Matadero).title("Matadero de Madrid"));
        final LatLng MRio = new LatLng(40.397388, -3.709733);
        mMap.addMarker(new MarkerOptions().position(MRio).title("Madrid Río"));
        final LatLng P7tetas = new LatLng(40.396989, -3.655977);
        mMap.addMarker(new MarkerOptions().position(P7tetas).title("Parque 7 Tetas"));
        final LatLng PlzCastilla = new LatLng(40.465849, -3.689517);
        mMap.addMarker(new MarkerOptions().position(PlzCastilla).title("Plaza Castilla"));
        final LatLng torresKio = new LatLng(40.466936, -3.688501);
        mMap.addMarker(new MarkerOptions().position(torresKio).title("Torres Kio"));
        final LatLng CuatroTorres = new LatLng(40.476376, -3.687764);
        mMap.addMarker(new MarkerOptions().position(CuatroTorres).title("Cuatro Torres"));
        final LatLng MCera = new LatLng(40.425145, -3.691336);
        mMap.addMarker(new MarkerOptions().position(MCera).title("Museo de cera"));
        final LatLng PlzColon = new LatLng(40.424817, -3.689113);
        mMap.addMarker(new MarkerOptions().position(PlzColon).title("Plaza de Colón"));
        final LatLng Ventas = new LatLng(40.432270, -3.663737);
        mMap.addMarker(new MarkerOptions().position(Ventas).title("Plaza de Toros Las Ventas"));
        final LatLng Bernabeu = new LatLng(40.452992, -3.688404);
        mMap.addMarker(new MarkerOptions().position(Bernabeu).title("Santiago Bernabéu"));

    }
}
