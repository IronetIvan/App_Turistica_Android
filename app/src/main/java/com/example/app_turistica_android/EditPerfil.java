package com.example.app_turistica_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Explicacion.OnBoardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EditPerfil extends AppCompatActivity {
    TextView txtCorreo;
    EditText txtApellido, txtPassword, txtConfirPassword;
    Button btnGuardar;
    private static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editperfil);
        instancias();
        acciones();

    }

    private void instancias() {
        txtCorreo = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.editText5);
        txtPassword = findViewById(R.id.editText7);
        txtConfirPassword = findViewById(R.id.editext8);
        btnGuardar = findViewById(R.id.btnGuardar);
        firebaseAuth = firebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }



    private void acciones() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*boolean validarOk= true;
                if(validarOk == true){

                }else if(validarOk == false){

                }*/



                //Intent intent = new Intent(EditPerfil.this, OnBoardActivity.class);
                //startActivity(intent);

            }
        });

    }
}

