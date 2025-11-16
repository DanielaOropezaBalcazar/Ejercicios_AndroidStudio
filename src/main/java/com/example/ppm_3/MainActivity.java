package com.example.ppm_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btppm;
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

        btppm = findViewById(R.id.btPPM);
        btppm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "Invoca ppm", Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.mnuAlta){
                            //Toast.makeText(MainActivity.this, "Alta", Toast.LENGTH_SHORT).show();
                            Intent invoAlta = new Intent(MainActivity.this, AltaActivity.class);
                            startActivity(invoAlta);
                        }
                        if(id == R.id.mnuLista){
                            //Toast.makeText(MainActivity.this, "Lista", Toast.LENGTH_SHORT).show();
                            Intent invoLista = new Intent(MainActivity.this, ListaActivity.class);
                            startActivity(invoLista);
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }
}