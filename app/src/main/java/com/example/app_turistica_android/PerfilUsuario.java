package com.example.app_turistica_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Maps.MapsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PerfilUsuario extends AppCompatActivity {

    LinearLayout misRutas, favoritos, informacionCuenta, editarPerfil;
    FloatingActionButton volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilusuario);
        instancias();
        acciones();

    }

    private void instancias() {
        misRutas.findViewById(R.id.btnMisRutas);
        favoritos.findViewById(R.id.btnFavoritos);
        informacionCuenta.findViewById(R.id.btnInformacion);
        editarPerfil.findViewById(R.id.btnEditarPerfil);
        volver.findViewById(R.id.btnvolver);
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


}
