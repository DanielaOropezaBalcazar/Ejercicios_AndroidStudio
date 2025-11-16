package com.example.grafo_demo_01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GraphView extends View {

    private Paint nodoPaint, textoPaint, aristaPaint;
    private Grafo grafo;
    private Nodo nodoSeleccionado;

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        nodoPaint = new Paint();
        nodoPaint.setColor(Color.BLUE);
        nodoPaint.setStyle(Paint.Style.FILL);

        textoPaint = new Paint();
        textoPaint.setColor(Color.WHITE);
        textoPaint.setTextSize(40);

        aristaPaint = new Paint();
        aristaPaint.setColor(Color.BLACK);
        aristaPaint.setStrokeWidth(5);

        grafo = new Grafo();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar aristas primero
        for (Arista a : grafo.getAristas()) {
            Nodo origen = grafo.getNodos().get(a.origenId - 1);
            Nodo destino = grafo.getNodos().get(a.destinoId - 1);
            canvas.drawLine(origen.x, origen.y, destino.x, destino.y, aristaPaint);
            // Mostrar el peso en el medio de la arista
            float midX = (origen.x + destino.x) / 2;
            float midY = (origen.y + destino.y) / 2;
            canvas.drawText(String.valueOf(a.peso), midX, midY, textoPaint);
        }

        // Dibujar nodos encima
        for (Nodo n : grafo.getNodos()) {
            canvas.drawCircle(n.x, n.y, 60, nodoPaint);
            canvas.drawText(n.nombre, n.x - 20, n.y + 10, textoPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                nodoSeleccionado = getNodoEnPos(x, y);
                if (nodoSeleccionado == null) {
                    grafo.agregarNodo("N" + (grafo.getNodos().size() + 1), x, y);
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (nodoSeleccionado != null) {
                    nodoSeleccionado.x = x;
                    nodoSeleccionado.y = y;
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                nodoSeleccionado = null;
                break;
        }
        return true;
    }

    private Nodo getNodoEnPos(float x, float y) {
        for (Nodo n : grafo.getNodos()) {
            float dx = x - n.x;
            float dy = y - n.y;
            if (Math.sqrt(dx * dx + dy * dy) < 60) {
                return n;
            }
        }
        return null;
    }
}
