package com.example.ppm_3;

public class ModeloProducto {
    private int idProducto;
    private String nombreProducto;
    private float costoUnitario;

    public ModeloProducto(int idProducto, String nombreProducto, float costoUnitario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.costoUnitario = costoUnitario;
    }

    public ModeloProducto(String nombreProducto, float costoUnitario) {
        this.nombreProducto = nombreProducto;
        this.costoUnitario = costoUnitario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    @Override
    public String toString() {
        return "ModeloProducto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", costoUnitario=" + costoUnitario +
                '}';
    }
}
