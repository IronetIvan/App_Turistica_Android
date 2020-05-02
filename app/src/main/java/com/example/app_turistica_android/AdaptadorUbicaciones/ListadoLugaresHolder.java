package com.example.app_turistica_android.AdaptadorUbicaciones;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListadoLugaresHolder extends RecyclerView.ViewHolder {

    ImageView foto;
    TextView nombre, tipo;

    public ListadoLugaresHolder(@NonNull View itemView, ImageView foto, TextView nombre, TextView tipo) {
        super(itemView);
        this.foto = foto;
        this.nombre = nombre;
        this.tipo = tipo;
    }
}
