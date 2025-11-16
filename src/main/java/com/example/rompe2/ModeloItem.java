package com.example.rompe2;

public class ModeloItem {
    private int foto;
    private String boton;
    private String titulo, detalle;

    public ModeloItem(int foto, String boton, String titulo, String detalle) {
        this.foto = foto;
        this.boton = boton;
        this.titulo = titulo;
        this.detalle = detalle;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
