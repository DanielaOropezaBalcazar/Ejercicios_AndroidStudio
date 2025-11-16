package com.example.recy;

public class ModeloItem {
    private int foto;
    private String titulo, detalle;

    public ModeloItem(int foto, String titulo, String detalle) {
        this.foto = foto;
        this.titulo = titulo;
        this.detalle = detalle;
    }

    public int getFoto() {
        return foto;
    }
    public void setFoto(int foto) {
        this.foto = foto;
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
