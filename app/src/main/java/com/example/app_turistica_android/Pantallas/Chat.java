package com.example.app_turistica_android.Pantallas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.Mensajes.AdapterMensajes;
import com.example.app_turistica_android.Mensajes.Mensaje;
import com.example.app_turistica_android.Mensajes.MensajeEnviar;
import com.example.app_turistica_android.Mensajes.MensajeRecibir;
import com.example.app_turistica_android.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    private CircleImageView imagenperfil;
    private TextView nombreusuario;
    private RecyclerView listadomensajes;
    private EditText txtMensajes;
    private FloatingActionButton btnEnviar;

    private AdapterMensajes adapterMensajes;
    private String fotoPerfilCadena;
    //private static final int FOTO_SUBIDA = 1;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage almacenamiento;
    private StorageReference refAlmacenamiento;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        instancias();
        acciones();
    }

    private void acciones() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new MensajeEnviar(txtMensajes.getText().toString(),nombreusuario.getText().toString(),fotoPerfilCadena,"1", ServerValue.TIMESTAMP));
                txtMensajes.setText("");
            }
        });

        /*btnEnviarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);
                intentGaleria.setType("image/jpg");
                intentGaleria.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intentGaleria, "Selecciona una imagen"), FOTO_SUBIDA);
            }
        });*/

        //se llama a este metodo cuando insertamos un nuevo objeto
        adapterMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                adapterMensajes.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void instancias() {
        imagenperfil = findViewById(R.id.fotoperfil);
        nombreusuario = findViewById(R.id.nombrechat);
        listadomensajes = findViewById(R.id.mensajes);
        txtMensajes = findViewById(R.id.txtmensajes);
        btnEnviar = findViewById(R.id.btnEnviarMsg);
        //btnEnviarImagen = findViewById(R.id.enviarfoto);
        fotoPerfilCadena ="";

        adapterMensajes = new AdapterMensajes(this);
        LinearLayoutManager lm= new LinearLayoutManager(this);
        listadomensajes.setLayoutManager(lm);
        listadomensajes.setAdapter(adapterMensajes);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat"); //Sala de chat
        almacenamiento = FirebaseStorage.getInstance();
    }

    //El listado se va siempre a la ultima posicion
    private void ScrollBar(){
        listadomensajes.scrollToPosition(adapterMensajes.getItemCount() - 1);
    }


    /*//Cuando elijas un intent devuelve un resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FOTO_SUBIDA && resultCode == RESULT_OK){
            Uri u = data.getData();
            refAlmacenamiento = almacenamiento.getReference("imagenesChat");
            final StorageReference fotoReferencia = refAlmacenamiento.child(u.getLastPathSegment()); //Obtiene la clave de la imagen para que sean diferentes
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MensajeEnviar m = new MensajeEnviar("Has recibido una imagen", u.toString(), nombreusuario.getText().toString(), "",
                            "2", ServerValue.TIMESTAMP);

                    databaseReference.push().setValue(m);
                }
            });
        }
    }*/
}
