package com.example.ppm_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Helper extends SQLiteOpenHelper {
    static final String nombreDB = "miDB";
    static final int verDB = 2; // ante cambio, cambiar la version

    public Helper( Context context) {
        super(context, nombreDB, null, verDB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE producto(id_producto integer primary key autoincrement, nombre_producto text, costo_unitario float)"


        );

        sqLiteDatabase.execSQL(String.format("" +
                        "CREATE TABLE IF NOT EXISTS %s(id_venta integer primary key autoincrement, cantidad int, fecha  text, id_producto int, CONSTRAINT fk_producto FOREIGN KEY(id_producto) REFERENCES producto(id_producto))",
                "venta")
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS producto");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS venta");
        onCreate(sqLiteDatabase);
    }
}
