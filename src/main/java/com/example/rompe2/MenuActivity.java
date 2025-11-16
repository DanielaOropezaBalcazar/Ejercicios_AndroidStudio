package com.example.rompe2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    TextView tv1;
    Button btInvocarGameMode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv1 = findViewById(R.id.tvTitulo);

        btInvocarGameMode = findViewById(R.id.btInvocarGameMode);
        //btInvocar = findViewById(R.id.btInvoca3);

        btInvocarGameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviadorGameMode();
            }
        });

    }

    void enviadorGameMode() {
        // la clase "Intent" permite invocar un activity
        Intent intent = new Intent(this, GameModeActivity.class); // (origen, destino)
        // metodo para ver la llamada
        startActivity(intent);
    }

}