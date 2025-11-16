package com.example.ppm_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Controller {
    Helper helper;

    public Controller(Context context) {
        this.helper = new Helper(context);
    }

    public long altaProducto(ModeloProducto objProd) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre_producto", objProd.getNombreProducto());
        valoresParaInsertar.put("costo_unitario", objProd.getCostoUnitario());

        return sql.insert("producto", null, valoresParaInsertar);
    }

    public ArrayList<ModeloProducto> obtenerProductos() {
        ArrayList<ModeloProducto> listaProductos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String[] columnasAConsultar = {"id_producto","nombre_producto", "costo_unitario"};
        Cursor cursor = baseDeDatos.query(
                "producto",
                columnasAConsultar,
                null,
                null,
                null,
                null,
                "nombre_producto"
        );

        if (cursor == null) {
            return listaProductos;
        }
        if (!cursor.moveToFirst()) return listaProductos;
        do {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            float costo = cursor.getFloat(2);
            ModeloProducto objProd = new ModeloProducto(id, nombre, costo);
            listaProductos.add(objProd);
        } while (cursor.moveToNext());
        cursor.close();
        return listaProductos;
    }

    public int cambioProducto(ModeloProducto objProd) {

        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre_producto", objProd.getNombreProducto());
        valoresParaActualizar.put("costo_unitario", objProd.getCostoUnitario());
        // where id...
        String campoParaActualizar = "id_producto = ?";

        String[] argumentosParaActualizar = {String.valueOf(objProd.getIdProducto())};

        return baseDeDatos.update("producto", valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int bajaProducto(ModeloProducto productoEditado) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        // where id...
        String campoParaActualizar = "id_producto = ?";
        String[] argumentosParaActualizar = {String.valueOf(productoEditado.getIdProducto())};
        return baseDeDatos.delete("producto",  campoParaActualizar, argumentosParaActualizar);
    }





}
