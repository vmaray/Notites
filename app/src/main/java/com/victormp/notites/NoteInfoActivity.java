package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class NoteInfoActivity extends AppCompatActivity {
    // Definición de elementos de la interfaz de usuario
    protected TextView h1;
    protected TextView note_info;
    protected MaterialButton btnDeleteNote;
    protected MaterialButton btnCancel;

    // Definición de datos de las notas
    private int note_id = 0;
    private String note_text;

    // Definición del intent de cambio de actividad
    private Intent backToMain;
    // Definición de la BBDD
    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        // Inicialización de la BBDD SQLite
        db = new DataBaseSQL(this);

        // Inicialización de los elementos de la interfaz de usuario
        h1 = findViewById(R.id.h1_NoteInfo);
        note_info = findViewById((R.id.txt_NoteInfo));
        btnDeleteNote = findViewById(R.id.btnDeleteNote);
        btnCancel = findViewById(R.id.btnCancel_NoteInfo);

        // Obtener el ID y el texto de la nota de los extras del intent
        note_id = getIntent().getIntExtra("note_id", -1);
        note_text = getIntent().getStringExtra("note_text");

        // Actualizar la interfaz de usuario con el ID y el texto de la nota
        h1.append(": " + note_id);
        note_info.setText(note_text);

        // Listener para el botón de eliminar nota
        btnDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Elimina la nota de la base de datos utilizando el ID de la nota u muestra un mensaje de éxito
                db.deleteNote(note_id);
                Toast.makeText(NoteInfoActivity.this, getText(R.string.toast_DeleteNote), Toast.LENGTH_SHORT).show();

                //Cierra la BBDD
                db.close();

                // Vuelve a la pantalla principal automáticamente después de 2 segundos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Crea un intent para regresar a la actividad principal y finaliza la actividad actual
                        backToMain = new Intent(NoteInfoActivity.this, MainActivity.class);
                        startActivity(backToMain);
                        finish();
                    }
                }, 2000); // 2000 milisegundos (2 segundos)
            }
        });

        // Listener del botón cancelar para cancelar la operación y volver a inicio
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain = new Intent(NoteInfoActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });
    }
}