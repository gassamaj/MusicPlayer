package com.example.student.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Handler myHandler = new Handler();





    private static MediaPlayer song1;


    public Button playButtonVar;
    public Button  pauseButtonVar;
    public Button stopButtonVar;
    public Button fastforward;
    public TextView endTimeViewVar;
    public TextView currentTimeViewVar;



    private TextView title;
    private TextView author;

    public int  currentTimeMS;
    public int finalTimeMS;
    public int endMinutes;
    public int endSeconds;
    public int currentMinutes;
    public int currentSeconds;
    public int seekTime;
    public String songTitle;
    public String  songArtist;
    public TextView songTitleViewVar;
    public TextView songArtistViewVar;
    public SongObject thisSong;
    public String songID;
    private SeekBar mySongBarVar;
    public int songNumber;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent thisIntent = getIntent();

        private void playNewSong(int position){


//in this version, I called my MediaPlayer songPlayer

            if (song1 !=null){
                song1.stop();
                song1.release();
                song1=null;
            }
            song1= MediaPlayer.create(this, thisSong.songID);
            song1.seekTo(0);


        }

        thisSong = SongPicker.songList.get(position);

        songNumber = Integer.parseInt( thisIntent.getStringExtra("songMessage"));
        thisSong = SongPicker.songList.get(songNumber);

        if (song1 !=null){
            song1.stop();
            song1.release();
            song1=null;
        }


        songTitle = thisSong.title;
        songArtist = thisSong.artist;

        title = (TextView) findViewById(R.id.songTitleView);
        author = (TextView) findViewById(R.id.songArtistView);

        title.setText(songTitle);
        author.setText(songArtist);
        song1 = MediaPlayer.create(this, thisSong.songID);
        playButtonVar = (Button) findViewById(R.id.txtVw_playSong);
        pauseButtonVar = (Button) findViewById(R.id.txtVw_pauseSong);
        stopButtonVar = (Button) findViewById(R.id.txtVw_stopSong);

        TextView songTitleViewVar = (TextView) findViewById(R.id.text);
        endTimeViewVar = (TextView) findViewById(R.id.txtVw_viewbar);
        currentTimeViewVar = (TextView) findViewById(R.id.txtVw_endtime);
        finalTimeMS = song1.getDuration();
        mySongBarVar= (SeekBar) findViewById(R.id.txtVw_seekBar1);
        mySongBarVar.setMax((int) finalTimeMS);


        songTitleViewVar = (TextView) findViewById(R.id.songTitleView);
        songArtistViewVar = (TextView) findViewById(R.id.songArtistView);


        currentTimeMS = song1.getCurrentPosition();
        endMinutes = (int) (finalTimeMS / 1000 / 60);
        endSeconds = ((int) (finalTimeMS / 1000)) %60;
        currentMinutes =(int) (currentTimeMS/1000/60);
        currentSeconds = ((int)(currentTimeMS/1000)) %60;
        endTimeViewVar.setText(endMinutes+ " min, "+endSeconds + " sec");
        currentTimeViewVar.setText( currentMinutes+ " min, "+currentSeconds + " sec");
        myHandler.postDelayed(UpdateSongTime, 100);

        mySongBarVar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekTime=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                song1.seekTo(seekTime);
                currentTimeMS = seekTime;
            }
        });


        songArtist = thisSong.title;
        songTitle = thisSong.artist;
        songTitleViewVar.setText(songTitle);
        songArtistViewVar.setText(songArtist);




        playSong();
    }

// use thisSong.songID to get the ID number for the song for the MediaPlayer and Metadata
// use thisSong.artist and thisSong.title to get the artist and title now

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTimeMS = song1.getCurrentPosition();
           int currentMinutes =(int) (currentTimeMS/1000/60);
            int currentSeconds = ((int)(currentTimeMS/1000)) %60;
            currentTimeViewVar.setText(currentMinutes+ " min, "+currentSeconds + " sec");

            mySongBarVar.setProgress((int) currentTimeMS);

            myHandler.postDelayed(this, 100);





        }
    };

    public void playSongClick(View view){
        playSong();
    }

    public void playSong() {
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



public  void fastforwardSong (View view) {

    if (currentTimeMS < finalTimeMS - 5000) {


        song1.seekTo(currentTimeMS + 5000);
    }
    Context context = getApplicationContext();
    CharSequence text = "rewind";
    int duration = Toast.LENGTH_SHORT;
    Toast myMessage = Toast.makeText(context, text, duration);
    myMessage.show();
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
    }}





