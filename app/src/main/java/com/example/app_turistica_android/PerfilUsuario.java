package com.example.app_turistica_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class PerfilUsuario extends AppCompatActivity {

    Button misRutas, addRutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_perfilusuario);
        instancias();
        acciones();

    }

    private void acciones() {
        misRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this,"Muestra Rutas del usuario", Toast.LENGTH_SHORT).show();
            }
        });

        addRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PerfilUsuario.this,"Permite al usuario agregar rutas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void instancias() {
        misRutas.findViewById(R.id.btn_misrutas);
        addRutas.findViewById(R.id.btn_addrutas);
    }
}
