package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNoteActivity extends AppCompatActivity {
    protected MaterialButton btnSaveNote;
    protected MaterialButton btnCancel;
    protected TextInputEditText inputContent;

    private Intent backToMain;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        db = new DataBaseSQL(this);

        btnSaveNote = findViewById(R.id.btnSave_addNote);
        btnCancel = findViewById(R.id.btnCancel_addNote);
        inputContent = findViewById(R.id.TextInputEditText_newNote);


        // Botones de navegación
        // Guardar nota
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputContent != null) {
                    String new_note = inputContent.getText().toString();

                    if (!new_note.isEmpty()) {
                        db.addNote(new_note);
                        Toast.makeText(AddNoteActivity.this, "Nota creada correctamente", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                backToMain = new Intent(AddNoteActivity.this, MainActivity.class);
                                startActivity(backToMain);
                                db.closeDB();
                                finish();
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(AddNoteActivity.this, "Nota obligatoria", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // Cancelar y retroceder a inicio
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });












    }
}