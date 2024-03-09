package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class NoteInfoActivity extends AppCompatActivity {
    protected TextView h1;
    protected TextView note_info;
    protected MaterialButton btnDeleteNote;
    protected MaterialButton btnCancel;

    private int note_id = 0;
    private String note_text;

    private Intent backToMain;

    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        db = new DataBaseSQL(this);

        h1 = findViewById(R.id.h1_NoteInfo);
        note_info = findViewById((R.id.txt_NoteInfo));
        btnDeleteNote = findViewById(R.id.btnDeleteNote);
        btnCancel = findViewById(R.id.btnCancel_NoteInfo);

        note_id = getIntent().getIntExtra("note_id", -1);
        note_text = getIntent().getStringExtra("note_text");

        h1.append(": " + note_id);
        note_info.setText(note_text);

        btnDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note_id);
                Toast.makeText(NoteInfoActivity.this, getText(R.string.toast_DeleteNote), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backToMain = new Intent(NoteInfoActivity.this, MainActivity.class);
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
                backToMain = new Intent(NoteInfoActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });
    }
}