package com.example.ppm_3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {


    ArrayList<ModeloProducto> lista;
    AdaptadorRVP adaptador;
    RecyclerView rvProducto;
    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = new ArrayList<>();
        adaptador = new AdaptadorRVP(lista);
        rvProducto = findViewById(R.id.rvListaProducto);

        controller = new Controller(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvProducto.setLayoutManager(mLayoutManager);
        rvProducto.setItemAnimator(new DefaultItemAnimator());
        rvProducto.setAdapter(adaptador);

        refrescarListaDeProductos();

        rvProducto.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rvProducto, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ModeloProducto prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                ModeloProducto prodElegido = lista.get(position);
                cambio(prodElegido);
            }
        }));



    }

    public void refrescarListaDeProductos() {

        if (adaptador == null) return;
        lista = controller.obtenerProductos();
        adaptador.setListaDeProductos(lista);
        adaptador.notifyDataSetChanged();
    }

    void baja(final ModeloProducto objProd){
        AlertDialog.Builder alertaBaja = new AlertDialog.Builder(this);
        alertaBaja.setTitle("Baja de productos");
        alertaBaja.setMessage(objProd.toString());
        alertaBaja.setCancelable(false);
        alertaBaja.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long res = controller.bajaProducto(objProd);
                if(res<0){
                    Toast.makeText(getApplicationContext(),"Error en la baja", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"succes, exito enla baja "+res, Toast.LENGTH_LONG).show();
                    refrescarListaDeProductos();
                }
            }
        });

        alertaBaja.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertaBaja.show();
    }

    void cambio(final ModeloProducto objProd){

        View subView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_cambio_prod, null);
        final EditText etNombre, etCosto;
        etNombre = subView.findViewById(R.id.etNuevoNombre);
        etCosto = subView.findViewById(R.id.etNuevoCosto);

        etNombre.setText(objProd.getNombreProducto());
        etCosto.setText(String.valueOf(objProd.getCostoUnitario()));
        AlertDialog.Builder ale =new  AlertDialog.Builder(ListaActivity.this);
        ale.setTitle("Cambio en producto");
        ale.setView(subView);
        ale.setCancelable(false);
        ale.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objProd.setNombreProducto(etNombre.getText().toString());
                objProd.setCostoUnitario(Float.parseFloat(etCosto.getText().toString()));
                long res = controller.cambioProducto(objProd);
                if(res < 0){
                    Toast.makeText(getApplicationContext(),"Error en el cambio", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"succes, exito en  e cambio "+res, Toast.LENGTH_LONG).show();
                    refrescarListaDeProductos();
                }
            }
        });

        ale.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ale.show();

    }

}
