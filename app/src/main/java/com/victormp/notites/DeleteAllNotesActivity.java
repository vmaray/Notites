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

    protected MaterialToolbar toolbar;
    protected TextView title;
    protected TextView h1DeleteAllNotes;

    protected MaterialButton btnDeleteAllNotes;
    protected MaterialButton btnCancel;

    private Intent backToMain;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all_notes);

        db = new DataBaseSQL(this);

        toolbar = findViewById(materialToolbar);
        title = findViewById(R.id.txtApp_toolbar);
        h1DeleteAllNotes = findViewById(R.id.textView_h1_deleteAllNotes);
        btnDeleteAllNotes = findViewById(R.id.btnDeleteAllNotes_deleteAllNotes);
        btnCancel = findViewById(R.id.btnCancel_deleteAllNotes);




        // Botones de navegación
        // Eliminar todas las notas
        btnDeleteAllNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllNotes();
                Toast.makeText(DeleteAllNotesActivity.this, "Se han borrado todas las notas correctamente", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backToMain = new Intent(DeleteAllNotesActivity.this, MainActivity.class);
                        startActivity(backToMain);
                        db.closeDB();
                        finish();
                    }
                }, 2000);
            }
        });


        // Cancelar y retroceder a inicio
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain = new Intent(DeleteAllNotesActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

    }
}