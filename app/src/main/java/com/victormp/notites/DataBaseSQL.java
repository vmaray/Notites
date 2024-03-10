package com.victormp.notites;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Clase que gestiona la base de datos SQLite para almacenar las notas
public class DataBaseSQL extends SQLiteOpenHelper {
    protected SQLiteDatabase db;

    // Nombre de la base de datos y versión
    private static final String DATABASE_NAME = "Notitas.db";
    private static final int DATABASE_VERSION = 1;
    // Nombre de la tabla de notas
    private static final String TABLE_NOTES = "Notas";

    // Constructor de la clase base SQLiteOpenHelper
    public DataBaseSQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nota TEXT)");
    }

    // Actualizar la base de datos elimnando la tabla anterior
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
    }

    // Método para agregar una nueva nota a la base de datos
    public void addNote(String note) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NOTES + " (nota) VALUES ('"+note+"')");
    }

    // Método para obtener todas las notas de la base de datos
    public ArrayList<String> getAllNotes() {
        // Crear una lista para almacenar todas las notas
        ArrayList<String> all_notes = new ArrayList<>();
        // Declarar un objeto Cursor para almacenar el resultado de la consulta
        Cursor cursor;
        // Acceder a la base de datos en modo de lectura
        db = this.getReadableDatabase();

        // Ejecutar la consulta SQL para seleccionar todas las notas ordenadas por ID de forma ascendente
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTES + " ORDER BY id ASC" , null); //  Almacenar el resultado en el cursor

        // Mover el cursor a la primera fila
        cursor.moveToFirst();

        // Recorrer todas las filas guardando cada nota a la lista de notas
        while (!cursor.isAfterLast()) {
            // Obtener el ID y texto de la fila actual y concatenarlo con ' .- '
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String noteText = cursor.getString(cursor.getColumnIndex("nota"));
            String note = id + ".- " + noteText;
            // Añadir la nota al array
            all_notes.add(note);
            // Avanzar el cursor
            cursor.moveToNext();
        }
        // Cerrar el cursor y liberar recursos
        cursor.close();
        return all_notes; // Devuelve el ArrayList<> con la lista de todas las notas
    }


    // Método para eliminar una nota de la base de datos
    public void deleteNote(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES + " WHERE id = " + id);
    }

    // Método para eliminar todas las notas de la base de datos
    public void deleteAllNotes() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES);
        // Restablecer el contador del ID
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NOTES + "'");
    }

}
