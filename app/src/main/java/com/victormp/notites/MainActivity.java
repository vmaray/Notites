package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView view;
    protected MaterialToolbar toolbar;
    protected TextView title;
    protected MaterialButton btnAddNote;
    protected MaterialButton btnConfig;
    private Intent addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (RecyclerView) findViewById(R.id.recView_main);
        toolbar = (MaterialToolbar) findViewById(R.id.materialToolbar);
        title = (TextView) findViewById(R.id.txtApp_main);
        btnAddNote = (MaterialButton) findViewById(R.id.btnAddNote_main);
        btnConfig = (MaterialButton) findViewById(R.id.btnConfig_main);


        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addNote);
            }
        });
    }
}