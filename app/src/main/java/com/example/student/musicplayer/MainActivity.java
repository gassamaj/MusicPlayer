package com.example.student.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Handler myHandler = new Handler();





   // private SeekBar mySongBarVar = new SeekBar();//

    private MediaPlayer song1;
    public Button playButtonVar;
    public Button  pauseButtonVar;
    public Button stopButtonVar;

   public TextView endTimeViewVar;
    public TextView currentTimeViewVar;

public int  currentTimeMS;
    public int finalTimeMS;
    public int endMinutes;
    public int endSeconds;
    public int currentMinutes;
    public int currentSeconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        song1 = MediaPlayer.create(this, R.raw.bargainsinatuxedo);
        playButtonVar = (Button) findViewById(R.id.txtVw_playSong);
        pauseButtonVar = (Button) findViewById(R.id.txtVw_pauseSong);
        stopButtonVar = (Button) findViewById(R.id.txtVw_stopSong);
        endTimeViewVar = (TextView) findViewById(R.id.txtVw_viewbar);
        currentTimeViewVar = (TextView) findViewById(R.id.txtVw_endtime);
        finalTimeMS = song1.getDuration();
        currentTimeMS = song1.getCurrentPosition();
        endMinutes = (int) (finalTimeMS / 1000 / 60);
        endSeconds = ((int) (finalTimeMS / 1000)) %60;
        currentMinutes =(int) (currentTimeMS/1000/60);
//        currentMinutes + currentTimeMS.song1.getCurrentPosition();
        currentSeconds = ((int)(currentTimeMS/1000)) %60;
        endTimeViewVar.setText(endMinutes+ " min, "+endSeconds + " sec");
        currentTimeViewVar.setText( currentMinutes+ " min, "+currentSeconds + " sec");
        myHandler.postDelayed(UpdateSongTime, 100);


    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTimeMS = song1.getCurrentPosition();
           int currentMinutes =(int) (currentTimeMS/1000/60);
            int currentSeconds = ((int)(currentTimeMS/1000)) %60;
            currentTimeViewVar.setText(currentMinutes+ " min, "+currentSeconds + " sec");

            myHandler.postDelayed(this, 100);





        }
    };

    public void playSong(View view) {
        playButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(true);
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

    public void stopSong(View view) {
        pauseButtonVar.setEnabled(false);
        stopButtonVar.setEnabled(false);
        playButtonVar.setEnabled(true);
        song1.pause();
        Context context = getApplicationContext();
        CharSequence text = "Stop";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
        song1.seekTo( (int) (0000) );



    }

    public void rewindSong(View view) {

        if(currentTimeMS > 5000){

             song1.seekTo( currentTimeMS - 5000);
        }

        Context context = getApplicationContext();
        CharSequence text = "rewind";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
    }

    public void fastforwardSong(View view) {

        if(currentTimeMS < finalTimeMS- 5000){

            song1.seekTo( currentTimeMS + 5000);
        }

        Context context = getApplicationContext();
        CharSequence text = "fastfoward";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
    }

}
