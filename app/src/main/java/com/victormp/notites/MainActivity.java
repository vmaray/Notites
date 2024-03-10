package com.victormp.notites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    // Definición de elementos de la interfaz de usuario
    protected ListView listView;
    protected MaterialToolbar toolbar;
    protected TextView title;
    protected MaterialButton btnAddNote;
    protected MaterialButton btnConfig;

    // Definición de datos de las notas
    private String note_data;
    private String[] note_arr;
    private int note_id = 0;
    private String note_text;
    private ArrayList<String> all_notes;

    // Definición de los intents de cambio de actividad
    private Intent addNote;
    private Intent config;
    private Intent noteInfo;

    // Definición de la BBDD
    protected DataBaseSQL db;

    // Definición del adapter para ListView
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de la base de datos SQL
        db = new DataBaseSQL(this);

        // Inicialización de los elementos de la interfaz de usuario
        listView = findViewById(R.id.ListView_main);
        toolbar = findViewById(R.id.materialToolbar);
        title = findViewById(R.id.txtApp_toolbar);
        btnAddNote = findViewById(R.id.btnAddNote_main);
        btnConfig = findViewById(R.id.btnConfig_main);

        // Creación de una lista para almacenar todas las notas y un adaptador para la lista
        all_notes = new ArrayList<>();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, all_notes);
        listView.setAdapter(adapter);

        // Listener para manejar cada elemento de la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Al hacer clic en una nota, se extrae la información de la nota y se inicia una nueva actividad para mostrar los detalles
                note_data = adapterView.getItemAtPosition(i).toString();
                note_arr = note_data.split("\\.- ");
                if (note_arr.length > 1) {
                    note_id = Integer.parseInt(note_arr[0]);
                    note_text = note_arr[1];

                    // Creación de un intent para lanzar la actividad de detalles de la nota
                    noteInfo = new Intent(MainActivity.this, NoteInfoActivity.class);
                    // Añadir a la nueva actividad los paquetes con el id y texto de la nota
                    noteInfo.putExtra("note_id", note_id);
                    noteInfo.putExtra("note_text", note_text);
                    // Ir a la actividad con la información de la nota
                    startActivity(noteInfo);
                }
            }
        });

        // Listener para el botón de configuración / eliminar todas las notas
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config = new Intent(MainActivity.this, DeleteAllNotesActivity.class);
                startActivity(config);
            }
        });

        // Listener para el botón de crear una nueva nota
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addNote);
            }
        });
    }

    /*
    Al retroceder a la pantalla principal se veían las notas que había antes de la última operación realizada.
    Por ello, con onResume(), se recarga el ListView con la BBDD al regresar a esta pantalla y no se muestra información antigua
    */
    @Override
    protected void onResume() {
        super.onResume();
        // Limpia el ListView de todas las notas existentes
        all_notes.clear();
        // Cargar todas las notas de la BBDD
        all_notes.addAll(db.getAllNotes());
        // Actualiza las notas en el ListView a través del adapter
        adapter.notifyDataSetChanged();
    }

}