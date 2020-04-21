package com.example.app_turistica_android.AdaptadorUbicaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.R;
import com.example.app_turistica_android.utils.Lugares;

import java.util.ArrayList;

public class Adapt_recyler_detalles extends RecyclerView.Adapter<Adapt_recyler_detalles.HolderDetalles> {
    Context ctx;
    ArrayList<Lugares> listaLugares;
    OnMiRecyclerListener listener;

    public Adapt_recyler_detalles(Context ctx, ArrayList<Lugares> listaLugares) {
        this.ctx = ctx;
        this.listaLugares = listaLugares;
        listener = (OnMiRecyclerListener) ctx;
    }
    @NonNull
    @Override
    public Adapt_recyler_detalles.HolderDetalles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.recycler_layout_det, parent, false);
        HolderDetalles miHolder = new HolderDetalles(view);
        return miHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapt_recyler_detalles.HolderDetalles holder, int position) {
        final Lugares lugarActual = listaLugares.get(position);
        //holder.getImagen().setImageResource(lugarActual.getImagen());
        holder.getNombre().setText(lugarActual.getNombre());
        //holder.getDescripcion().setText(lugarActual.getDescripcion());
        holder.getLayoutDetalles().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMiRecycler(lugarActual);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    class HolderDetalles extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView descripcion, nombre;
        private LinearLayout layoutDetalles;

        public HolderDetalles(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_fila);
            nombre = itemView.findViewById(R.id.nombre);
            descripcion = itemView.findViewById(R.id.descripcion);
            layoutDetalles = itemView.findViewById(R.id.layoutDetalles);
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getDescripcion() {
            return descripcion;
        }

        public LinearLayout getLayoutDetalles() {
            return layoutDetalles;
        }
    }
    public interface OnMiRecyclerListener {
        public void onMiRecycler(Lugares lugar);
    }
}
