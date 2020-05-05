package com.example.app_turistica_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.app_turistica_android.Explicacion.OnBoardActivity;
import com.example.app_turistica_android.Maps.MapsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LogIn extends AppCompatActivity {

    Button btnInicio,btnInvitado;
    ImageButton btnRegistrar, btninicioGoogle ;
    EditText nombre, password;
    RadioButton rSesion;

    private boolean activarRadioB;
    private static  final String Preference_Estado_RadioButton= "estado";

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();

    }

    private void acciones() {
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPreferencias();
                IniciarSesion();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Registro.class);
                startActivity(intent);
            }
        });


        btninicioGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, OnBoardActivity.class);
                startActivity(intent);

            }
        });

        rSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activarRadioB){
                    rSesion.setChecked(false);
                }
                activarRadioB = rSesion.isChecked();
            }
        });
    }

    private void instancias() {
        btnInicio = findViewById(R.id.btnInicio);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btninicioGoogle = findViewById(R.id.btninicioGoogle);
        nombre = findViewById(R.id.txtcorreo);
        password = findViewById(R.id.txtpassword);
        btnInvitado= findViewById(R.id.btnInvitado);
        rSesion = findViewById(R.id.radioSesion);
        activarRadioB = rSesion.isChecked(); //desactivado
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }


    private void IniciarSesion(){

        final String email = nombre.getText().toString().trim();
        String passwd = password.getText().toString().trim();

        //Se comprueba que los textfield no esten vacios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(passwd)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Iniciando sesion...");
        progressDialog.show();

        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(LogIn.this, "Bienvenido: " + nombre.getText(), Toast.LENGTH_LONG).show();
                            Intent iniciarSesion = new Intent(LogIn.this, MapsActivity.class);
                            guardarPreferencias();
                            // sacas el uid del usuario logeado;
                            iniciarSesion.putExtra("uid",firebaseAuth.getCurrentUser().getUid());
                            LogIn.this.startActivity(iniciarSesion);
                            finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(LogIn.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LogIn.this, "Usuario o contraseña incorrectos ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }
    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("credenciales",MODE_PRIVATE);
        preferences.edit().putBoolean(Preference_Estado_RadioButton,rSesion.isChecked()).apply();

        String usuario = nombre.getText().toString();
        String pass = password.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("pass",pass);

        editor.commit();
    }
    }
