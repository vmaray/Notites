package com.victormp.notites;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNoteActivity extends AppCompatActivity {

    protected MaterialToolbar toolbar;
    protected TextView title;
    protected MaterialButton btnSaveNote;
    protected MaterialButton btnCancel;
    protected TextView h1AddNote;
    protected TextInputLayout txtLayout;
    protected TextInputEditText inputContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = findViewById(R.id.materialToolbar);
        title = findViewById(R.id.txtApp_main);
        btnSaveNote = findViewById(R.id.btnSave_addNote);
        btnCancel = findViewById(R.id.btnCancel_addNote);
        h1AddNote = findViewById(R.id.textView_h1_AddNote);
        txtLayout = findViewById(R.id.textInputLayout_newNote);
        inputContent = findViewById(R.id.TextInputEditText_newNote);

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputContent != null) {
                    String new_note = inputContent.getText().toString().trim();

                    if (new_note.isEmpty()) {
                        Toast.makeText(AddNoteActivity.this, "Nota obligatoria", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddNoteActivity.this, "Nota creada correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });











    }
}