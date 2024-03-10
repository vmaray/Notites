package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddNoteActivity extends AppCompatActivity {
    // Definición de elementos de la interfaz de usuario
    protected MaterialButton btnSaveNote;
    protected MaterialButton btnCancel;
    protected TextInputEditText inputContent;

    // Definición del intent de cambio de actividad
    private Intent backToMain;

    // Definición de la BBDD
    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Inicialización de la BBDD SQLite
        db = new DataBaseSQL(this);

        // Inicialización de los elementos de la interfaz de usuario
        btnSaveNote = findViewById(R.id.btnSave_addNote);
        btnCancel = findViewById(R.id.btnCancel_addNote);
        inputContent = findViewById(R.id.TextInputEditText_newNote);


        // Listener del botón de guardar la nota en la BBDD
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verifica si el campo de entrada de contenido no es nulo
                if (inputContent != null) {
                    // Obtiene el texto de la nueva nota del campo de entrada y limpia los espacios en blanco al principio y final del texto
                    String new_note = inputContent.getText().toString().trim();

                    // Verifica que el texto no esté vacío
                    if (!new_note.isEmpty()) {
                        // Agrega la nueva nota a la base de datos y muestra un mensaje de éxito
                        db.addNote(new_note);
                        Toast.makeText(AddNoteActivity.this, getText(R.string.toast_AddNote), Toast.LENGTH_SHORT).show();
                        //Cierra la BBDD
                        db.close();

                        // Vuelve a la pantalla principal automáticamente después de 2 segundos
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Crea un intent para regresar a la actividad principal y finaliza la actividad actual
                                backToMain = new Intent(AddNoteActivity.this, MainActivity.class);
                                startActivity(backToMain);
                                finish();
                            }
                        }, 2000); // 2000 milisegundos (2 segundos)
                    } else {
                        // Muestra un mensaje de advertencia si el texto de la nueva nota está vacío
                        Toast.makeText(AddNoteActivity.this, getText(R.string.toast_MissingNote), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // Listener del botón cancelar para cancelar la operación y volver a inicio
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });












    }
}