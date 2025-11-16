package com.example.recy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewDataHolder> {

    ArrayList<ModeloItem> lista;
    public Adaptador(ArrayList<ModeloItem> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public Adaptador.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_02, null, false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewDataHolder holder, int position) {
        holder.ivfoto.setImageResource(lista.get(position).getFoto());
        holder.tvtitulo.setText(lista.get(position).getTitulo());
        holder.tvdetalle.setText(lista.get(position).getDetalle());

    }

    @Override
    public int getItemCount() {
        return lista.size(); // retornar el size de la lista
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder {

        ImageView ivfoto;
        TextView tvtitulo, tvdetalle;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);

            ivfoto = itemView.findViewById(R.id.ivFoto);
            tvtitulo = itemView.findViewById(R.id.tvTitulo);
            tvdetalle = itemView.findViewById(R.id.tvDetalle);



        }
    }
}
