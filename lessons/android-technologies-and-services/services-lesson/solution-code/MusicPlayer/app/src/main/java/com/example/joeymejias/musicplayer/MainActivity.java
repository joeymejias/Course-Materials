package com.example.joeymejias.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int PLAY = R.drawable.ic_play_arrow_black_24dp;
    private static final int PAUSE = R.drawable.ic_pause_black_24dp;

    private Button mButton;
    private MyService.Music mMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
