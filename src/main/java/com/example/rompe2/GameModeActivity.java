package com.example.rompe2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GameModeActivity extends AppCompatActivity {

    ListView lvdata;
    Adaptador adaptador;
    ArrayList<ModeloItem> lista;
    Button btvolver, btJugarClassicMode, btJugarCustomMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btvolver = findViewById(R.id.btVolverMenu);

        lvdata = findViewById(R.id.lvData);
        lista = new ArrayList<>();
        adaptador = new Adaptador(this, lista);
        lvdata.setAdapter(adaptador);
        cargaLista();

        lvdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GameModeActivity.this, "Elegido: "+lista.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });

        btvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // el metodo "finish" devuelve a su inmediato INVOCADOR, en este caso al MainActivity
                finish();

            }
        });

    }

    void cargaLista() {
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Jugar Classic Mode", "Classic Mode", "En este modo de juego el puzzle debes ordenar los numeros del 1 al 8 en un tablero de 3x3 moviendo una pieza a la vez, Podras con el reto? "));
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, "Jugar Custom Mode", "Custom Mode", "En este modo de juego debes resolver el mismo puzzle que en Classic Mode, pero tu decides que imagen tomara el rompecabezas. (Puedes elegir una imagen de tu galeria)."));
        lista.add(new ModeloItem(R.drawable.ic_launcher_background, null, "Proximamente", "Modo aun sin desbloquear"));

    }

}