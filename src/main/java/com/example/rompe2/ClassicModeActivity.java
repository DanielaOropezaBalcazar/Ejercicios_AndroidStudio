package com.example.rompe2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassicModeActivity extends AppCompatActivity {

    // Variables principales

    List<TextView> vNodo = new ArrayList<>();
    int filaPivot = 2, columnaPivot = 2;
    int posicionPivot = 8;

    Button bt_mezclar, bt_ordenar, bt_volver_menu;

    // Estado original del tablero
    List<String> textosOriginales = new ArrayList<>();
    List<Integer> coloresOriginales = new ArrayList<>();

    // Cronómetro
    TextView tvcronometro;
    Handler handler = new Handler();
    int segundos = 0;
    long tiempoInicio = 0;
    boolean corriendo = false;

    // Ciclo de vida
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_classic_mode);

        // Ajustar insets para que no tape la barra de estado
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicializarVistas();
        configurarListeners();
        guardarEstadoOriginal();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detenerCronometro();
    }

    // Inicialización
    private void inicializarVistas() {
        vNodo.add(findViewById(R.id.tvA));  vNodo.add(findViewById(R.id.tvB));  vNodo.add(findViewById(R.id.tvC));
        vNodo.add(findViewById(R.id.tvD));  vNodo.add(findViewById(R.id.tvE));  vNodo.add(findViewById(R.id.tvF));
        vNodo.add(findViewById(R.id.tvG));  vNodo.add(findViewById(R.id.tvH));  vNodo.add(findViewById(R.id.tvX));

        bt_mezclar = findViewById(R.id.btMezclar);
        bt_ordenar = findViewById(R.id.btOrdenar);
        bt_volver_menu = findViewById(R.id.btVolverMenu);

        tvcronometro = findViewById(R.id.tvCronometro);
    }

    private void configurarListeners() {
        // Movimiento de fichas
        for (int i = 0; i < vNodo.size(); i++) {
            final int x = i;
            vNodo.get(x).setOnClickListener(v -> {
                int filaNodo = x / 3;
                int columnaNodo = x % 3;

                // Solo mover si es adyacente al pivot
                if ((filaNodo == filaPivot && Math.abs(columnaNodo - columnaPivot) == 1) ||
                        (columnaNodo == columnaPivot && Math.abs(filaNodo - filaPivot) == 1)) {

                    if (!corriendo) {
                        iniciarCronometro();
                    }
                    swap(x, filaNodo, columnaNodo);
                }
            });
        }

        // Mezclar
        bt_mezclar.setOnClickListener(v -> mezclarPuzzle());

        // Ordenar
        bt_ordenar.setOnClickListener(v -> ordenarPuzzle());

        // Volver al menú
        bt_volver_menu.setOnClickListener(v -> enviadorMenu());
    }

    private void guardarEstadoOriginal() {
        for (TextView tv : vNodo) {
            textosOriginales.add(tv.getText().toString());
            Drawable bg = tv.getBackground();
            if (bg instanceof android.graphics.drawable.ColorDrawable) {
                int color = ((android.graphics.drawable.ColorDrawable) bg).getColor();
                coloresOriginales.add(color);
            } else {
                coloresOriginales.add(0xFFFFFFFF); // Blanco por defecto
            }
        }
    }

    // Lógica del puzzle
    private void swap(int x, int fNodo, int cNodo) {
        String msg = vNodo.get(x).getText().toString();
        Drawable color = vNodo.get(x).getBackground();

        vNodo.get(x).setText(vNodo.get(posicionPivot).getText());
        vNodo.get(x).setBackground(vNodo.get(posicionPivot).getBackground());

        vNodo.get(posicionPivot).setText(msg);
        vNodo.get(posicionPivot).setBackground(color);

        posicionPivot = x;
        filaPivot = fNodo;
        columnaPivot = cNodo;

        if (estaResuelto()) {
            detenerCronometro();
            Toast.makeText(this, "¡Puzzle resuelto en " + tvcronometro.getText() + " segundos!", Toast.LENGTH_LONG).show();
        }
    }

    private void mezclarPuzzle() {
        List<String> textos = new ArrayList<>();
        List<Drawable> fondos = new ArrayList<>();
        for (TextView tv : vNodo) {
            textos.add(tv.getText().toString());
            fondos.add(tv.getBackground());
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < vNodo.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        for (int i = 0; i < vNodo.size(); i++) {
            int idx = indices.get(i);
            vNodo.get(i).setText(textos.get(idx));
            vNodo.get(i).setBackground(fondos.get(idx));

            if (textos.get(idx).equals("X")) {
                posicionPivot = i;
                filaPivot = i / 3;
                columnaPivot = i % 3;
            }
        }

        detenerCronometro();
        reiniciarCronometro();
    }

    private void ordenarPuzzle() {
        for (int i = 0; i < vNodo.size(); i++) {
            vNodo.get(i).setText(textosOriginales.get(i));
            vNodo.get(i).setBackgroundColor(coloresOriginales.get(i));

            if (textosOriginales.get(i).equals("X")) {
                posicionPivot = i;
                filaPivot = i / 3;
                columnaPivot = i % 3;
            }
        }

        detenerCronometro();
        reiniciarCronometro();
    }

    private boolean estaResuelto() {
        for (int i = 0; i < vNodo.size(); i++) {
            if (!vNodo.get(i).getText().toString().equals(textosOriginales.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void enviadorMenu() {
        startActivity(new Intent(this, MenuActivity.class));
    }

    // Cronómetro
    final Runnable cronRunnable = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            if (!corriendo) return;

            long tiempoActual = System.currentTimeMillis();
            long tiempoTranscurrido = tiempoActual - tiempoInicio;

            int horas = (int) (tiempoTranscurrido / 3600000);
            int minutos = (int) ((tiempoTranscurrido % 3600000) / 60000);
            int segundos = (int) ((tiempoTranscurrido % 60000) / 1000);
            int milisegundos = (int) (tiempoTranscurrido % 1000);

            tvcronometro.setText(String.format("%02d:%02d:%02d.%03d",
                    horas, minutos, segundos, milisegundos));

            handler.postDelayed(this, 10);
        }
    };

    private void iniciarCronometro() {
        if (corriendo) return;
        corriendo = true;
        tiempoInicio = System.currentTimeMillis() - (segundos * 1000L);
        handler.post(cronRunnable);
    }

    private void detenerCronometro() {
        corriendo = false;
        handler.removeCallbacks(cronRunnable);
    }

    private void reiniciarCronometro() {
        detenerCronometro();
        segundos = 0;
        tvcronometro.setText("00:00:00");
    }
}
