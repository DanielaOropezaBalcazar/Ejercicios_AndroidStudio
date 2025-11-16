package com.example.rompe2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomModeActivity extends AppCompatActivity {

    Button bt_mezclar, bt_ordenar, bt_cargar_imagen, bt_volver_menu;
    TextView tvcronometro;

    // para el Cronometro
    Handler handler = new Handler();
    long tiempoInicio = 0;
    boolean corriendo = false;

    // para cargar la imagen
    static final int PICK_IMAGE = 1;
    ImageView[] casillas; // 9 posiciones (3x3)
    ImageView ivReferencia;
    Bitmap imagenOriginal;

    // estado del puzzle
    int filaPivot = 2, columnaPivot = 2;
    int posicionPivot = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_mode);

        bt_mezclar = findViewById(R.id.btMezclar);
        bt_ordenar = findViewById(R.id.btOrdenar);
        bt_cargar_imagen = findViewById(R.id.btCargarImagen);
        bt_volver_menu = findViewById(R.id.btVolverMenu);
        tvcronometro = findViewById(R.id.tvCronometro);
        ivReferencia = findViewById(R.id.ivReferencia);

        // Vincular casillas como ImageView
        casillas = new ImageView[]{
                findViewById(R.id.ivA), findViewById(R.id.ivB), findViewById(R.id.ivC),
                findViewById(R.id.ivD), findViewById(R.id.ivE), findViewById(R.id.ivF),
                findViewById(R.id.ivG), findViewById(R.id.ivH), findViewById(R.id.ivX)
        };

        // Clicks en las casillas
        for (int i = 0; i < casillas.length; i++) {
            final int pos = i;
            casillas[i].setOnClickListener(v -> moverPieza(pos));
        }

        bt_mezclar.setOnClickListener(v -> mezclarPuzzle());
        bt_ordenar.setOnClickListener(v -> ordenarPuzzle());
        bt_cargar_imagen.setOnClickListener(v -> abrirGaleria());
        bt_volver_menu.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }

    // ---------- Puzzle ----------
    private void moverPieza(int x) {
        int filaNodo = x / 3;
        int columnaNodo = x % 3;

        if ((filaNodo == filaPivot && Math.abs(columnaNodo - columnaPivot) == 1) ||
                (columnaNodo == columnaPivot && Math.abs(filaNodo - filaPivot) == 1)) {

            if (!corriendo) iniciarCronometro();

            DrawableSwap(x);
            posicionPivot = x;
            filaPivot = filaNodo;
            columnaPivot = columnaNodo;

            if (estaResuelto()) {
                detenerCronometro();
                Toast.makeText(this, "¡Puzzle resuelto en " + tvcronometro.getText() + "!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void DrawableSwap(int x) {
        Bitmap bmp1 = getBitmapFromImageView(casillas[x]);
        Bitmap bmp2 = getBitmapFromImageView(casillas[posicionPivot]);

        casillas[x].setImageBitmap(bmp2);
        casillas[posicionPivot].setImageBitmap(bmp1);
    }

    private Bitmap getBitmapFromImageView(ImageView iv) {
        if (iv.getDrawable() == null) return null;
        iv.setDrawingCacheEnabled(true);
        iv.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(iv.getDrawingCache());
        iv.setDrawingCacheEnabled(false);
        return bmp;
    }

    private void mezclarPuzzle() {
        // 1️⃣ Extraer los bitmaps actuales de todas las casillas (incluyendo la X)
        List<Bitmap> piezas = new ArrayList<>();
        for (ImageView iv : casillas) {
            piezas.add(getBitmapFromImageView(iv)); // getBitmapFromImageView devuelve null si es X
        }

        // 2️⃣ Mezclar las piezas
        Collections.shuffle(piezas);

        // 3️⃣ Reasignar las piezas mezcladas
        for (int i = 0; i < casillas.length; i++) {
            casillas[i].setImageBitmap(piezas.get(i));
            // si es la última pieza (antes era null), agregar fondo gris
            if (piezas.get(i) == null) {
                casillas[i].setBackgroundColor(Color.LTGRAY);
                posicionPivot = i;
                filaPivot = i / 3;
                columnaPivot = i % 3;
            } else {
                casillas[i].setBackgroundColor(Color.TRANSPARENT);
            }
        }

        reiniciarCronometro();
    }

    private void ordenarPuzzle() {
        if (imagenOriginal != null) {
            cortarYAsignarImagen(imagenOriginal);
            reiniciarCronometro();
        }
    }

    private boolean estaResuelto() {
        // TODO: implementar validación de orden correcto con las piezas originales
        return false;
    }

    // ---------- Cronómetro ----------
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

    void iniciarCronometro() {
        corriendo = true;
        tiempoInicio = System.currentTimeMillis();
        handler.post(cronRunnable);
    }

    void detenerCronometro() {
        corriendo = false;
        handler.removeCallbacks(cronRunnable);
    }

    void reiniciarCronometro() {
        detenerCronometro();
        tvcronometro.setText("00:00:00.000");
    }

    // ---------- Imagen desde Galeria----------
    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                imagenOriginal = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                cortarYAsignarImagen(imagenOriginal);
                ivReferencia.setImageURI(uri);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void cortarYAsignarImagen(Bitmap original) {
        // ---------- 1️⃣ Determinar tamaño del recorte cuadrado ----------
        int ancho = original.getWidth();
        int alto = original.getHeight();
        int lado = Math.min(ancho, alto); // tomar el menor lado para cuadrado

        // coordenadas para centrar el recorte
        int offsetX = (ancho - lado) / 2;
        int offsetY = (alto - lado) / 2;

        // recortar la imagen al cuadrado centrado
        Bitmap cuadrado = Bitmap.createBitmap(original, offsetX, offsetY, lado, lado);

        // ---------- 2️⃣ Dividir en 3x3 ----------
        int tamCelda = lado / 3;
        int index = 0;
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                Bitmap parte = Bitmap.createBitmap(cuadrado, col * tamCelda, fila * tamCelda, tamCelda, tamCelda);

                if (index < casillas.length) {
                    casillas[index].setImageBitmap(parte);
                }
                index++;
            }
        }

        // ---------- 3️⃣ Última casilla vacía ----------
        casillas[8].setImageDrawable(null);
        casillas[8].setBackgroundColor(Color.LTGRAY);

    }


}
