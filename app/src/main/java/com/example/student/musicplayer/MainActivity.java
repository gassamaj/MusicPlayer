package com.example.student.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song1;
    public Button playButtonVar;
    public Button  pauseButtonVar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        song1 = MediaPlayer.create(this, R.raw.bargainsinatuxedo);
        playButtonVar = (Button) findViewById(R.id.txtVw_playSong);
        pauseButtonVar = (Button) findViewById(R.id.txtVw_pauseSong);

    }

    public void playSong(View view) {
        playButtonVar.setEnabled(false);
        pauseButtonVar.setEnabled(true);
        song1.start();

        Context context = getApplicationContext();
        CharSequence text = "Play";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
}
    public void pauseSong(View view) {
        pauseButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
       song1.pause();

        Context context = getApplicationContext();
        CharSequence text = "Pause";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();

    }
}
