package com.example.app_turistica_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Maps.MapsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilUsuario extends AppCompatActivity {
    TextView txtUsuario;
    LinearLayout misRutas, favoritos, informacionCuenta, editarPerfil;
    FloatingActionButton volver;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilusuario);
        instancias();
        acciones();

    }

    private void instancias() {
        txtUsuario = findViewById(R.id.txtUsuario);
        misRutas = findViewById(R.id.btnMisRutas);
        favoritos = findViewById(R.id.btnFavoritos);
        informacionCuenta = findViewById(R.id.btnInformacion);
        editarPerfil = findViewById(R.id.btnEditarPerfil);
        volver = findViewById(R.id.btnvolver);
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

        informacionCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this,"Permite al usuario ver su informacion de cuenta", Toast.LENGTH_SHORT).show();
            }
        });

        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuario.this, EditPerfil.class);
                startActivity(intent);
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuario.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarDatos(){
        DatabaseReference nodoUsuarios = myRef.getDatabase().getReference().child("usuarios");
        nodoUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String usuario = dataSnapshot.child("usuario").getValue().toString();
                txtUsuario.setText(usuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}
}
