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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPerfil extends AppCompatActivity {
    TextView txtCorreo;
    EditText txtUsuario, txtPassword, txtConfirPassword;
    Button btnGuardar;
    private static final String TAG = "MainActivity";
    //private FirebaseAuth firebaseAuth;
    //private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editperfil);
        instancias();
        acciones();
        traerDatos();
    }

    private void instancias() {
        txtCorreo = findViewById(R.id.txtNombre);
        txtUsuario = findViewById(R.id.editText5);
        txtPassword = findViewById(R.id.editText7);
        txtConfirPassword = findViewById(R.id.editext8);
        btnGuardar = findViewById(R.id.btnGuardar);
        //firebaseAuth = firebaseAuth.getInstance();
        //database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    private void traerDatos(){

        myRef.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String correo = dataSnapshot.child("email").getValue().toString();
                    String usuario = dataSnapshot.child("nombre_usuario").getValue().toString();
                    txtCorreo.setText(correo);
                    txtUsuario.setText(usuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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

