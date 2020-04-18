package com.example.app_turistica_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_turistica_android.Explicacion.OnBoardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    EditText txtNombreUsuario, txtCorreo, txtPassword, txtConfirPassword;
    Button btnRegsitrarse;
    private static final String TAG = "MainActivity";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        instancias();
        acciones();
        registarUsuarios();
    }

    private void instancias() {
        txtCorreo = findViewById(R.id.txtNombre);
        txtNombreUsuario = findViewById(R.id.editText5);
        txtPassword = findViewById(R.id.editText7);
        txtConfirPassword = findViewById(R.id.editext8);
        btnRegsitrarse = findViewById(R.id.btnRegistrar);
        firebaseAuth = firebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    private void registarUsuarios() {
        String email = txtCorreo.getText().toString().trim();
        String contrseña = txtPassword.getText().toString().trim();
        String confirContra = txtConfirPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contrseña)) {
            Toast.makeText(this, "Se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        /*if(contrseña != confirContra){
            Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return; validar contraseña repetidas ERROR
        }*/

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, contrseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Se ha registrado el usuario con el email: " + txtCorreo.getText(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registro.this, "Se ha registrado el usuario", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void acciones() {
        btnRegsitrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validarEmail(txtCorreo) == true){
                    registarUsuarios();
                    vaciarCampos();
                    Intent intent = new Intent(Registro.this, OnBoardActivity.class);
                    startActivity(intent);
                }


            }
        });

    }

    public void vaciarCampos() {
        txtCorreo.setText("");
        txtPassword.setText("");
        txtConfirPassword.setText("");
        txtNombreUsuario.setText("");

    }

   private boolean validarEmail(EditText email) {

        //Revisar condiciones

       String emailInput = email.getText().toString();

       if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return true;
       } else if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
           Toast.makeText(this, "El email introducido no es valido", Toast.LENGTH_SHORT).show();
           return false;
       } else {
           Toast.makeText(this, "El email esta vacio", Toast.LENGTH_SHORT).show();
           return false;
       }

   }
}


