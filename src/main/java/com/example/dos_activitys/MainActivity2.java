package com.example.dos_activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    TextView tvDato;
    Button btVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i(":p", " Se esta ejecutando onCreate(), en el MainActivity 2");

        tvDato = findViewById(R.id.tvDataRecibida);
        btVolver = findViewById(R.id.btVolver);

        Intent intent = getIntent();
        String data = intent.getStringExtra("mi_data"); // para instanciarlo con un valor
        tvDato.setText(data);

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // el metodo "finish" devuelve a su inmediato INVOCADOR, en este caso al MainActivity
                finish();
                Log.i(":p", " finish (de MainActivity 2) was here..."); // para rastrear errores, abrir el "LogCat"

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(":p", " Se esta ejecutando onStart(), en el MainActivity 2");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(":p", " Se esta ejecutando onResume(), en el MainActivity 2");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(":p", " Se esta ejecutando onPause(), en el MainActivity 2");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(":p", " Se esta ejecutando onStop(), en el MainActivity 2");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(":p", " Se esta ejecutando onDestroy(), en el MainActivity 2");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(":p", " Se esta ejecutando onRestart(), en el MainActivity 2");

    }

}