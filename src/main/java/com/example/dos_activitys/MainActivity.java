package com.example.dos_activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etData;
    Button btInvocador2, btInvocador3;

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

        Log.i(":p", " Se esta ejecutando onCreate(), en el MainActivity");

        etData = findViewById(R.id.etDato);
        btInvocador2 = findViewById(R.id.btInvoca2);
        btInvocador3 = findViewById(R.id.btInvoca3);

        btInvocador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviador2();
            }
        });

        btInvocador3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviador3();
            }
        });

    }

    void enviador2() {
        // para llevar el dato al "MainActivity2"
        Bundle bolsa = new Bundle(); // bolsa de datos
        bolsa.putString("mi_data", etData.getText().toString());

        Log.i(":p", " enviador2 was here..."); // para rastrear errores, abrir el "LogCat"

        // la clase "Intent" permite invocar un activity
        Intent intent = new Intent(this, MainActivity2.class); // (origen, destino)
        // para que el Intent lleve la "bolsa"
        intent.putExtras(bolsa);
        // metodo para ver la llamada
        startActivity(intent);
    }

    void enviador3() {

        // la clase "Intent" permite invocar un activity
        Intent intent = new Intent(this, MainActivity3.class); // (origen, destino)
        // metodo para ver la llamada
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(":p", " Se esta ejecutando onStart(), en el MainActivity");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(":p", " Se esta ejecutando onResume(), en el MainActivity");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(":p", " Se esta ejecutando onPause(), en el MainActivity");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(":p", " Se esta ejecutando onStop(), en el MainActivity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(":p", " Se esta ejecutando onDestroy(), en el MainActivity");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(":p", " Se esta ejecutando onRestart(), en el MainActivity");

    }
}