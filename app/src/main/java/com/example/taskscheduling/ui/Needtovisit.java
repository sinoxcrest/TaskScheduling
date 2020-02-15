package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskscheduling.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class Needtovisit extends AppCompatActivity {
EditText notename, noteplace, notedate;
Button notestore;
    private SensorManager sensorManager;
    private boolean isMoved = false;
    private View view;
    private long lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needtovisit);

        notename = findViewById(R.id.noteeventname);
//        noteplace = findViewById(R.id.noteeventplace);
//        notedate = findViewById(R.id.noteeventdate);
        notestore = findViewById(R.id.notestore);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        notestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Savenote();

            }
        });
    }



    private void Savenote(){
            try{
                PrintStream printStream = new PrintStream(openFileOutput("notes.txt", MODE_PRIVATE | MODE_APPEND));
                printStream.println(notename.getText().toString() + "->" + "ddd" + "->" +
                      "gg");

                Toast.makeText(Needtovisit.this,"saved" + getFilesDir(), Toast.LENGTH_SHORT ).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(Needtovisit.this,"Unable to store" + getFilesDir(), Toast.LENGTH_SHORT ).show();
            }
    }






}
