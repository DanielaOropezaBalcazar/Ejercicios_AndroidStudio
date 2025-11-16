package com.example.grafo_demo_01;

public class Arista {
    public int origenId;
    public int destinoId;
    public int peso;
    public boolean dirigido;

    public Arista(int origenId, int destinoId, int peso, boolean dirigido) {
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.peso = peso;
        this.dirigido = dirigido;
    }
}
