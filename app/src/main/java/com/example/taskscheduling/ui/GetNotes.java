package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taskscheduling.R;
import com.example.taskscheduling.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.sql.StatementEvent;

public class GetNotes extends AppCompatActivity {
private ListView lv;
private Map<String, String> getnote;
String[] lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_notes);

        lv = findViewById(R.id.listnote);
        getnote = new HashMap<>();


        readNote();



        ArrayAdapter adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
               new ArrayList<String>(getnote.keySet()));

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String notesname = getnote.get(i);

                Intent intent = new Intent(GetNotes.this, NoteDetails.class);

                intent.putExtra("notesname",notesname);
                intent.putExtra("notesname",notesname);

                startActivity(intent);

            }
        });


//        lv.setOnItemClickListener((position, view, position, id) {
//
//            String key = parent.getItemAtPosition(position).toString();
//
//
//                String notesname = getnote.get(key);
//                String notesplace = getnote.get(key);
//                String notesdate = getnote.get(key);
//                Intent intent = new Intent(GetNotes.this, NoteDetails.class);
//                startActivity(intent);
//
//                intent.putExtra("notesname",notesname);
//                intent.putExtra("notesplace", notesplace);
//                intent.putExtra("notesdate", notesdate);
//
//
//        });

    }

    private void readNote(){
        try {
            FileInputStream fs = openFileInput("notes.txt");
            InputStreamReader isr = new InputStreamReader(fs);
            BufferedReader br = new BufferedReader(isr);

            String line = "";
            while ((line = br.readLine()) != null){
                String[] parts = line.split("->");
                getnote.put(parts[0], parts[1]);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
                }
