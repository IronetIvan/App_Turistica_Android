package com.example.app_turistica_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Maps.MapsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilUsuario extends AppCompatActivity {

    LinearLayout misRutas, favoritos, ayudaCuenta, editarPerfil,cerrarSesion;
    FloatingActionButton volver;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilusuario);
        uid = getIntent().getExtras().getString("uid");
        instancias();
        acciones();

    }

    private void instancias() {
        misRutas = findViewById(R.id.btnMisRutas);
        favoritos = findViewById(R.id.btnFavoritos);
        ayudaCuenta = findViewById(R.id.btnAyuda);
        editarPerfil = findViewById(R.id.btnEditarPerfil);
        volver = findViewById(R.id.btnvolver);
        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    private void acciones() {
        misRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this,"Muestra Rutas del usuario", Toast.LENGTH_SHORT).show();
            }
        });

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this,"Permite al usuario ver sus rutas favoritas", Toast.LENGTH_SHORT).show();
            }
        });

        ayudaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuario.this, Ayuda.class);
                startActivity(intent);
            }
        });

        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuario.this, EditPerfil.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PerfilUsuario.this, LogIn.class));
                finish();
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
