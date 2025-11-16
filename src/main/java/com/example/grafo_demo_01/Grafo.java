package com.example.grafo_demo_01;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Nodo> nodos;
    private ArrayList<Arista> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public ArrayList<Arista> getAristas() {
        return aristas;
    }

    public Nodo agregarNodo(String nombre, float x, float y) {
        Nodo n = new Nodo(nodos.size() + 1, nombre, x, y);
        nodos.add(n);
        return n;
    }

    public void eliminarNodo(Nodo n) {
        nodos.remove(n);
        aristas.removeIf(a -> a.origenId == n.id || a.destinoId == n.id);
    }

    public void agregarArista(int origenId, int destinoId, int peso, boolean dirigido) {
        aristas.add(new Arista(origenId, destinoId, peso, dirigido));
    }
}
