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

public class ListadoHolder extends RecyclerView.Adapter<ListadoHolder.HolderDetalles> {

    Context ctx;
    ArrayList<Lugares> listaLugares;
    //OnMiRecyclerListener listener;

    public ListadoHolder(Context ctx, ArrayList<Lugares> listaLugares) {
        this.ctx = ctx;
        this.listaLugares = listaLugares;
        //listener = (OnMiRecyclerListener) ctx;
    }
    @NonNull
    @Override
    public ListadoHolder.HolderDetalles onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_listado_lugares, parent, false);
        HolderDetalles miHolder = new HolderDetalles(view);
        return miHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoHolder.HolderDetalles holder, int position) {
        final Lugares lugarActual = listaLugares.get(position);

        holder.getFoto().setImageResource(Integer.parseInt(lugarActual.getFoto()));
        holder.getNombre().setText(lugarActual.getNombre());
        holder.getDescripcion().setText(lugarActual.getTipo());
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    class HolderDetalles extends RecyclerView.ViewHolder {

        private ImageView foto;
        private TextView descripcion, nombre;

        public HolderDetalles(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imagen_lugar);
            nombre = itemView.findViewById(R.id.nombre);
            //escripcion = itemView.findViewById(R.id.descripcion);
        }

       public ImageView getFoto(){return foto;}

        public TextView getNombre() {
            return nombre;
        }

        public TextView getDescripcion() {
            return descripcion;
        }

    }
    public interface OnMiRecyclerListener {
        public void onMiRecycler(Lugares lugar);
    }
}
