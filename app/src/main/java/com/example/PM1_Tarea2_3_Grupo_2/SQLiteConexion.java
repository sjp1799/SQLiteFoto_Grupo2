package com.example.PM1_Tarea2_3_Grupo_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.PM1_Tarea2_3_Grupo_2.transacciones.Transacciones;

public class SQLiteConexion extends SQLiteOpenHelper {

    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transacciones.CreateTableImagenes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Transacciones.DropeTableImagenes);
        onCreate(db);
    }

    public Cursor getAll() {
        return(getReadableDatabase().rawQuery("SELECT * FROM imagenes",null));
    }
    public void insert(byte[] bytes, String descripcion)
    {
        ContentValues cv = new ContentValues();

        cv.put(Transacciones.blobImagen,bytes);
        cv.put(Transacciones.descripcion,descripcion);
        Log.e("inserted", "inserted");
        getWritableDatabase().insert(Transacciones.tablaImagenes,Transacciones.idImagen,cv);

    }
    public byte[] getImage(Cursor c)
    {
        return(c.getBlob(1));
    }

}
