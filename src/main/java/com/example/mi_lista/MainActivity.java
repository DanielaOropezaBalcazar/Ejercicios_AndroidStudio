package com.example.mi_lista;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvdata;
    Adaptador adaptador;
    ArrayList<ModeloItem> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvdata = findViewById(R.id.lvData);
        lista = new ArrayList<>();
        adaptador = new Adaptador(this, lista);
        lvdata.setAdapter(adaptador);
        cargaLista();

        lvdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elegido: "+lista.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void cargaLista() {
        lista.add(new ModeloItem(R.drawable.auto_tesla, "Tesla", "Autito fachero facherito barato baratito. Compra ya"));
        lista.add(new ModeloItem(R.drawable.auto_suzuki, "Suzuki", "Autito fachero facherito barato baratito. Compra ya"));
        lista.add(new ModeloItem(R.drawable.auto_hot_wheels_2, "Hot Wheels", "Los mejores Autito fachero facheritos del mercado. Compra ya"));
        lista.add(new ModeloItem(R.drawable.auto_mercedes_benz, "Mercedes Benz", "Autito fachero facherito barato baratito. Compra ya"));

    }
}