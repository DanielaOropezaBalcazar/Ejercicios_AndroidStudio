package com.example.demo_1;

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

    EditText et_numero;
    Button bt_primo, bt_nuevo;
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

        et_numero = findViewById(R.id.etNumero);
        bt_primo = findViewById(R.id.btPrimo);
        tv_resultado = findViewById(R.id.tvResultado);
        bt_nuevo = findViewById(R.id.btNuevo);

        bt_primo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(et_numero.getText().toString());
                Primo obj = new Primo(n);

                if(obj.esPrimo() == true) {
                    tv_resultado.setText(String.valueOf(obj.n) + " si es primo");
                } else {
                    tv_resultado.setText(obj.n + " no es primo");
                }

            }
        });

        bt_nuevo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                et_numero.setText(null);
                tv_resultado.setText(null);
                et_numero.requestFocus();

            }
        });

    }
}
