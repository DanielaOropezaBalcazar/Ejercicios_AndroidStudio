package com.example.mi_lista;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// el adaptador se encarga de "inflar" cada uno de los items
public class Adaptador extends BaseAdapter {

    Context context;
    ArrayList<ModeloItem> lista;

    public Adaptador(Context context, ArrayList<ModeloItem> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModeloItem item = lista.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        ImageView ivfoto;
        TextView tvtitulo, tvdetalle;

        ivfoto = convertView.findViewById(R.id.ivFoto);
        tvtitulo = convertView.findViewById(R.id.tvTitulo);
        tvdetalle = convertView.findViewById(R.id.tvDestalle);

        ivfoto.setImageResource(item.getFoto());
        tvtitulo.setText(item.getTitulo());
        tvdetalle.setText(item.getDetalle());

        return convertView;
    }
}
