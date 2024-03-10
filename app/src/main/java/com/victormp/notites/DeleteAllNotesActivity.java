package com.victormp.notites;

import static com.victormp.notites.R.id.materialToolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class DeleteAllNotesActivity extends AppCompatActivity {
    // Definición de elementos de la interfaz de usuario
    protected MaterialToolbar toolbar;
    protected TextView title;
    protected TextView h1DeleteAllNotes;
    protected MaterialButton btnDeleteAllNotes;
    protected MaterialButton btnCancel;
    // Definición del intent de cambio de actividad
    private Intent backToMain;
    // Definición de la BBDD
    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all_notes);
        // Inicialización de la BBDD SQLite
        db = new DataBaseSQL(this);

        // Inicialización de los elementos de la interfaz de usuario
        toolbar = findViewById(materialToolbar);
        title = findViewById(R.id.txtApp_toolbar);
        h1DeleteAllNotes = findViewById(R.id.textView_h1_deleteAllNotes);
        btnDeleteAllNotes = findViewById(R.id.btnDeleteAllNotes_deleteAllNotes);
        btnCancel = findViewById(R.id.btnCancel_deleteAllNotes);

        // Listener del botón de eliminar todas las notas
        btnDeleteAllNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Elimina todas las notas de la base de datos y muestra un mensaje de éxito
                db.deleteAllNotes();
                Toast.makeText(DeleteAllNotesActivity.this, getText(R.string.toast_DeleteAllNotes), Toast.LENGTH_SHORT).show();
                //Cierra la BBDD
                db.close();

                // Vuelve a la pantalla principal automáticamente después de 2 segundos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Crea un intent para regresar a la actividad principal y finaliza la actividad actual
                        backToMain = new Intent(DeleteAllNotesActivity.this, MainActivity.class);
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
                backToMain = new Intent(DeleteAllNotesActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

    }
}