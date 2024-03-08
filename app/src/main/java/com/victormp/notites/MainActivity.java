package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ListView listView;
    protected MaterialToolbar toolbar;
    protected TextView title;
    protected MaterialButton btnAddNote;
    protected MaterialButton btnConfig;

    private String note_data;
    private String[] note_arr;
    private int note_id = 0;
    private String note_text;

    private Intent addNote;
    private Intent config;
    private Intent noteInfo;
    protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseSQL(this);

        listView = findViewById(R.id.ListView_main);
        toolbar = findViewById(R.id.materialToolbar);
        title = findViewById(R.id.txtApp_toolbar);
        btnAddNote = findViewById(R.id.btnAddNote_main);
        btnConfig = findViewById(R.id.btnConfig_main);

        ArrayList<String> all_notes = db.getAllNotes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, all_notes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                note_data = adapterView.getItemAtPosition(i).toString();
                note_arr = note_data.split("\\.- ");
                if (note_arr.length > 1) {
                    note_id = Integer.parseInt(note_arr[0]);
                    note_text = note_arr[1];

                    noteInfo = new Intent(MainActivity.this, NoteInfoActivity.class);

                    noteInfo.putExtra("note_id", note_id);
                    noteInfo.putExtra("note_text", note_text);

                    startActivity(noteInfo);
                }
            }
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config = new Intent(MainActivity.this, DeleteAllNotesActivity.class);
                startActivity(config);
            }
        });
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addNote);
            }
        });
    }
}