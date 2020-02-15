package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.taskscheduling.R;

public class NoteDetails extends AppCompatActivity {

    TextView viewnote, viewdate, viewplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        viewnote = findViewById(R.id.viewnotes);
//        viewplace = findViewById(R.id.viewplace);
//        viewdate = findViewById(R.id.viewdate);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String notesname = bundle.getString("notesname");
            viewnote.setText(notesname);

//            String notesplace = bundle.getString("notesplace");
//            viewplace.setText(notesplace);
//
//            String notesdate = bundle.getString("name");
//            viewdate.setText(notesdate);

        }
    }
}
