package com.example.rompe2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btplay;
        TextView tvtitulo, tvdetalle;

        ivfoto = convertView.findViewById(R.id.ivFoto);
        btplay = convertView.findViewById(R.id.btPlay);
        tvtitulo = convertView.findViewById(R.id.tvTitulo);
        tvdetalle = convertView.findViewById(R.id.tvDestalle);

        ivfoto.setImageResource(item.getFoto());
        btplay.setText(item.getBoton());
        tvtitulo.setText(item.getTitulo());
        tvdetalle.setText(item.getDetalle());

        // 👇 Aquí diferenciamos con position y lanzamos Activities
        btplay.setOnClickListener(v -> {
            switch (position) {
                case 0: // Classic Mode
                    Intent intentClassic = new Intent(context, ClassicModeActivity.class);
                    context.startActivity(intentClassic);
                    break;

                case 1: // Custom Mode
                    Intent intentCustom = new Intent(context, CustomModeActivity.class);
                    context.startActivity(intentCustom);
                    break;

                case 2: // Proximamente
                    Toast.makeText(context, "Modo aún no disponible", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        return convertView;
    }


}
