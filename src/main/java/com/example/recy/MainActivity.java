package com.example.recy;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvdata;
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

        // Instanciar
        lista = new ArrayList<>();
        adaptador = new Adaptador(lista);
        rvdata = findViewById(R.id.rvData);
        rvdata.setAdapter(adaptador);

        // Opciones de Vista
        //rvdata.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvdata.setLayoutManager(new GridLayoutManager(this, 2));

        cargaLista();
    }

    private void cargaLista() {
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Brasil", "No le pudo ganar a Bolivia"));
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Bolivia", "No le pudo ganar a Bolivia"));
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Chile", "No le pudo ganar a Bolivia"));
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Brasil", "No le pudo ganar a Bolivia"));
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Brasil", "No le pudo ganar a Bolivia"));

    }

}