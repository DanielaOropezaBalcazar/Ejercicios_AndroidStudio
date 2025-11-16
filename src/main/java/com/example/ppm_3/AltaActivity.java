package com.example.ppm_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AltaActivity extends AppCompatActivity {


    EditText etnombre, etcosto;
    Button btgraba, btsalir;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etnombre = findViewById(R.id.etNombreProducto);
        etcosto = findViewById(R.id.etCostoUnitario);

        btgraba = findViewById(R.id.btAltaProducto);
        btsalir = findViewById(R.id.btSaleDeAltaProducto);

        controller = new Controller(this);

        btgraba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etnombre.getText().toString();
                String costo = etcosto.getText().toString();
                if (nombre.isEmpty() || costo.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Error, campos vacios", Toast.LENGTH_LONG).show();
                } else {
                    long res = controller.altaProducto(new ModeloProducto(nombre, Float.parseFloat(costo)));
                    if (res < 0) {
                        Toast.makeText(getApplicationContext(), "Error, fracaso en la alta...", Toast.LENGTH_LONG).show();
                    } else {
                        limpiaCampos();
                        Toast.makeText(getApplicationContext(), "Succes, exito en la alta " + res, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    void limpiaCampos() {
        etcosto.setText(null);
        etnombre.setText(null);
        etnombre.requestFocus();
    }
}