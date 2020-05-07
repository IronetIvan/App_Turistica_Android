package com.example.app_turistica_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_turistica_android.Explicacion.OnBoardActivity;
import com.example.app_turistica_android.utils.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EditPerfil extends AppCompatActivity {
    TextView txtCorreo;
    EditText txtUsuario, txtPassword, txtConfirPassword;
    Button btnGuardar;
    private static final String TAG = "MainActivity";
    //private FirebaseAuth firebaseAuth;
    //private FirebaseDatabase database;
    //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editperfil);
        instancias();
        acciones();
        cargarDatos1();
        //traerDatos();
        //actDatos();
    }
    private void instancias() {
        txtCorreo = findViewById(R.id.txtNombre);
        txtUsuario = findViewById(R.id.editText5);
        txtPassword = findViewById(R.id.editText7);
        txtConfirPassword = findViewById(R.id.editext8);
        btnGuardar = findViewById(R.id.btnGuardar);
        myRef = FirebaseDatabase.getInstance().getReference();

    }

    private void cargaDatos(){
        final String uid = getIntent().getExtras().getString("uid");
        myRef.getDatabase().getReference().child("usuarios");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(uid!=null) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                        Usuarios usuarios = snapshot.getValue(Usuarios.class);
                        usuarios.getEmail(txtCorreo);
                        usuarios.getNombre(txtUsuario);
                        usuarios.getContraseña(txtPassword);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error al cargar datos de usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarDatos1(){
        final String uid = getIntent().getExtras().getString("uid");
        final DatabaseReference nodoUsuarios = myRef.getDatabase().getReference().child("usuarios");
        nodoUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.v("firebase",dataSnapshot.getKey());
                // nulo
                if (dataSnapshot.getKey().equals(uid)){
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = iterable.iterator();
                    while (iterator.hasNext()){
                        DataSnapshot actual = iterator.next();
                        // el objeto json entero
                        actual.getValue();

                        String correo = actual.child("correo").getValue().toString();
                        String usuario = actual.child("usuario").getValue().toString();
                        String contraseña = actual.child("contraseña").getValue().toString();
                        txtCorreo.setText(correo);
                        txtUsuario.setText(usuario);
                        txtPassword.setText(contraseña);

                        /*String correo = dataSnapshot.child("correo").getValue().toString();
                        String usuario = dataSnapshot.child("usuario").getValue().toString();
                        String contraseña = dataSnapshot.child("contraseña").getValue().toString();
                        */
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error al cargar datos de usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }






    /*private void traerDatos(){

        myRef.child("usuarios").child("LNp8sYdWfzQRJmC6tdjsSW8PReA3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //String value = dataSnapshot.getValue(String.class);
                //txtCorreo.setText(value);

                if (dataSnapshot.exists()) {


                    String correo = dataSnapshot.child("correo").getValue().toString();
                    String usuario = dataSnapshot.child("usuario").getValue().toString();
                    String contraseña = dataSnapshot.child("contraseña").getValue().toString();
                    txtCorreo.setText(correo);
                    txtUsuario.setText("Nombre usuario"+" "+usuario);
                    txtPassword.setText(contraseña);



                    /*for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        Usuarios user = dataSnapshot.getValue(Usuarios.class);
                        String nombreUsuario = user.getNombre_usuario();

                        Log.e("Nombre usuario",""+ nombreUsuario);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }*/



    private void acciones() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //actDatos();

                //ActPerfil();


                //Intent intent = new Intent(EditPerfil.this, OnBoardActivity.class);
                //startActivity(intent);

            }
        });

    }

}

