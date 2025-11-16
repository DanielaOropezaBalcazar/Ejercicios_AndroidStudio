package com.example.mis_fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvuno, tvdos, tvtres, tvcuatro, tvcinco;
    FrameLayout flcontainer;
    UnoFragment unoFragment;
    DosFragment dosFragment;
    TresFragment tresFragment;
    CuatroFragment cuatroFragment;
    CincoFragment cincoFragment;

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

        tvuno = findViewById(R.id.tv1);
        tvdos = findViewById(R.id.tv2);
        tvtres = findViewById(R.id.tv3);
        tvcuatro = findViewById(R.id.tv4);
        tvcinco = findViewById(R.id.tv5);

        unoFragment = new UnoFragment();
        dosFragment = new DosFragment();
        tresFragment = new TresFragment();
        cuatroFragment = new CuatroFragment();
        cincoFragment = new CincoFragment();

        flcontainer = findViewById(R.id.flContainer);

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer, unoFragment).commit();

        tvuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, unoFragment).commit();
            }
        });
        tvdos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, dosFragment).commit();
            }
        });
        tvtres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, tresFragment).commit();
            }
        });
        tvcuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, cuatroFragment).commit();
            }
        });
        tvcinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, cincoFragment).commit();
            }
        });

    }
}