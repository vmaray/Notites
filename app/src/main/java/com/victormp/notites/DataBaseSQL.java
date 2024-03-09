package com.victormp.notites;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {
    protected SQLiteDatabase db;
    private static final String DATABASE_NAME = "Notitas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTES = "Notas";

    public DataBaseSQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nota TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
    }

    // Añadir una nueva nota (id, note)
    public void addNote(String note) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NOTES + " (nota) VALUES ('"+note+"')");
    }

    // Mostrar todas las notas
    public ArrayList<String> getAllNotes() {
        ArrayList<String> all_notes = new ArrayList<>();
        Cursor cursor;
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NOTES + " ORDER BY id ASC";

        cursor = db.rawQuery(query , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String noteText = cursor.getString(cursor.getColumnIndex("nota"));
            String note = id + ".- " + noteText;
            all_notes.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return all_notes;
    }


    // Eliminar una nota
    public void deleteNote(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES + " WHERE id = " + id);
    }

    // Eliminar todas las notas
    public void deleteAllNotes() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NOTES + "'"); // Restablecer el contador del ID
    }


    public void closeDB() {
        db.close();
    }

}
