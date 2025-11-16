package com.example.rompe1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout parent;
    TextView tva, tvx, tvb, tvc, tvd, tve, tvf, tvg, tvh;
    List<TextView> vNodo = new ArrayList<>();
    int fPivote = 0, cPivote = 1;

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

        parent = findViewById(R.id.parent);
        tva = findViewById(R.id.tvA);
        tvx = findViewById(R.id.tvX);
        tvb = findViewById(R.id.tvB);
        tvc = findViewById(R.id.tvC);
        tvd = findViewById(R.id.tvD);
        tve = findViewById(R.id.tvE);
        tvf = findViewById(R.id.tvF);
        tvg = findViewById(R.id.tvG);
        tvh = findViewById(R.id.tvH);

        vNodo.add(tva);
        vNodo.add(tvx);
        vNodo.add(tvb);
        vNodo.add(tvc);
        vNodo.add(tvd);
        vNodo.add(tve);
        vNodo.add(tvf);
        vNodo.add(tvg);
        vNodo.add(tvh);

        for (int i = 0; i < vNodo.size(); i++) {
            final int e = i;

            vNodo.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int fila = e/3;
                    int colum = e%3;
                    //Toast.makeText(getApplicationContext(), vNodo.get(e).getText()+" ("+fila+", "+colum+")", Toast.LENGTH_SHORT).show();
                    if (fila == fPivote || colum == cPivote) {
                        if ( (colum-cPivote == 1 || cPivote-colum == 1) || (fila-fPivote == 1 || fPivote-fila == 1)) {
                            Toast.makeText(getApplicationContext(),  vNodo.get(e).getText()+" es adyacente", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                            Toast.makeText(getApplicationContext(),  vNodo.get(e).getText()+" NO", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


        /*
        // Para intercambio Logico
        tva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msa = tva.getText() .toString();
                Drawable bg_a = tva.getBackground();// para el background de A
                // darle a A los valores (valor y color) de X
                tva.setText(tvx.getText());
                tva.setBackground(tvx.getBackground());
                // darle a X los valores (valor y color) de A
                tvx.setText(msa);
                tvx.setBackground(bg_a);

            }
        });
        */

        /*
        // Para intercambio Fisico
        tva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexa = parent.indexOfChild(tva);
                int indexx = parent.indexOfChild(tvx);

                // remover fisicamente:
                parent.removeView(tva);
                parent.removeView(tvx);

                if (indexa < indexx) {
                    parent.addView(tvx, indexa);
                    parent.addView(tva, indexx);
                } else {
                    parent.addView(tva, indexx);
                    parent.addView(tvx, indexa);
                }
            }
        });

        tvb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexb = parent.indexOfChild(tvb);
                int indexx = parent.indexOfChild(tvx);

                // remover fisicamente:
                parent.removeView(tvb);
                parent.removeView(tvx);

                if (indexb < indexx) {
                    parent.addView(tvx, indexb);
                    parent.addView(tvb, indexx);
                } else {
                    parent.addView(tvb, indexx);
                    parent.addView(tvx, indexb);
                }
            }
        }); */
    }
}