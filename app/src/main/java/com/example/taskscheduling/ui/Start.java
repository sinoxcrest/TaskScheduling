package com.example.taskscheduling.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.taskscheduling.MainActivity;
import com.example.taskscheduling.R;


public class Start extends AppCompatActivity {

    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        player = MediaPlayer.create(Start.this, R.raw.hello);
        player.start();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                player.stop();
                startActivity(new Intent(Start.this, MainActivity.class));
                finish();
            }
        }, 9000);

    }
}
