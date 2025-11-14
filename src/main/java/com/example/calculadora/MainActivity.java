package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et_n1, et_n2;
    Button bt_suma, bt_resta, bt_producto, bt_division, bt_nuevo;
    TextView tv_resultado;

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

        et_n1 = findViewById(R.id.etNumero1);
        et_n2 = findViewById(R.id.etNumero2);

        bt_suma = findViewById(R.id.btSuma);
        bt_resta = findViewById(R.id.btResta);
        bt_producto = findViewById(R.id.btProducto);
        bt_division = findViewById(R.id.btDivision);
        bt_nuevo = findViewById(R.id.btNuevo);

        tv_resultado = findViewById(R.id.tvResultado);

        bt_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(et_n1.getText().toString());
                double n2 = Double.parseDouble(et_n2.getText().toString());
                double suma = n1+n2;
                tv_resultado.setText(String.valueOf(suma));
            }
        });

        bt_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(et_n1.getText().toString());
                double n2 = Double.parseDouble(et_n2.getText().toString());
                double resta = n1-n2;
                tv_resultado.setText(String.valueOf(resta));
            }
        });

        bt_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(et_n1.getText().toString());
                double n2 = Double.parseDouble(et_n2.getText().toString());
                double producto = n1*n2;
                tv_resultado.setText(String.valueOf(producto));
            }
        });

        bt_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1 = Double.parseDouble(et_n1.getText().toString());
                double n2 = Double.parseDouble(et_n2.getText().toString());
                double division = n1/n2;
                tv_resultado.setText(String.valueOf(division));
            }
        });

        bt_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_n1.setText(null);
                et_n2.setText(null);
                tv_resultado.setText(null);
                et_n1.requestFocus();
            }
        });


    }
}