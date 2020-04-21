package com.example.app_turistica_android.AdaptadorUbicaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_turistica_android.R;
import com.example.app_turistica_android.utils.Lugares;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MiHolder> {

    private ArrayList<Lugares> listaLugares;
    private Context context;
    private OnRecyclerListener listener;

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layoutlugar,parent,false);
        return new MiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycler.MiHolder holder, int position) {
        final Lugares lugar = listaLugares.get(position);
        holder.getTitulo().setText(lugar.getNombre());
        //holder.getImagen().setImageResource(lugar.getImagen());
        //holder.getNombre().setText(lugar.getDescripcion());
        holder.getImagen().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCocheSelected(lugar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }
    public interface OnRecyclerListener{
        void onCocheSelected(Lugares lugar);
    }

    class MiHolder extends RecyclerView.ViewHolder{
        TextView titulo, nombre;
        ImageView imagen;

        public MiHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            imagen = itemView.findViewById(R.id.imagen);
            nombre = itemView.findViewById(R.id.descripcion);
        }

        public TextView getTitulo() {
            return titulo;
        }

        public TextView getNombre() {
            return nombre;
        }

        public ImageView getImagen() {
            return imagen;
        }
    }
}
