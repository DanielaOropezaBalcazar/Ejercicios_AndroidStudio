package com.example.ppm_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRVP extends RecyclerView.Adapter<AdaptadorRVP.ViewDataHelper> {

    ArrayList<ModeloProducto> lista;

    public AdaptadorRVP(ArrayList<ModeloProducto> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos(ArrayList<ModeloProducto> listaDeProductos) {
        this.lista = listaDeProductos;
    }


    @Override
    public ViewDataHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_producto,parent, false);
        return new ViewDataHelper(view);
    }

    @Override
    public void onBindViewHolder( ViewDataHelper holder, int position) {
        holder.tvid.setText( String.valueOf(lista.get(position).getIdProducto() ));
        holder.tvnombre.setText(lista.get(position).getNombreProducto());
        holder.tvcosto.setText(String.valueOf(lista.get(position).getCostoUnitario() ));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHelper extends RecyclerView.ViewHolder {
        TextView tvid, tvnombre, tvcosto;
        public ViewDataHelper( View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tvIdProducto);
            tvnombre = itemView.findViewById(R.id.tvNombreProducto);
            tvcosto = itemView.findViewById(R.id.tvCostoProducto);

        }
    }
}
