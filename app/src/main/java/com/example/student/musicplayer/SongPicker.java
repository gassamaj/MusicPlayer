package com.example.student.musicplayer;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongPicker extends AppCompatActivity {
    public static SongObject baragains;
    public static SongObject letitSong;
    public static int[] songIDs;
    public static ArrayList<SongObject> songList;
    public static int songID;
    public static MediaMetadataRetriever songInfo  = new MediaMetadataRetriever();
    private SongAdapter mySongAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_picker);

        songList = new ArrayList<SongObject>();
        songIDs = new int[10];
        songIDs[0] = R.raw.bargainsinatuxedo;
        songIDs[1] = R.raw.glaringlyablaze;
        songIDs[2] = R.raw.atouchofyourmadman;
        songIDs[3] = R.raw.arduoustask;
        songIDs[4] = R.raw.blowtothehead;
        songIDs[5] = R.raw.fightingcombat;
        songIDs[6] = R.raw.letitrip;
        songIDs[7] = R.raw.losingaccusations;
        songIDs[8] = R.raw.nightmarestogo;
        songIDs[9] = R.raw.rabidcourage;

        for( int i=0;i<songIDs.length;i++) {
            songID = songIDs[i];

            Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + songID);
            songInfo.setDataSource(this, mediaPath);


            String songTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String  songArtist = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);


            songList.add( new SongObject(songID, songTitle, songArtist) );
        }

        mySongAdapter = new SongAdapter(this, songList);









        ListView listView = (ListView) findViewById(R.id.songListView);
        listView.setAdapter(mySongAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                launchPlayer(String.valueOf(position));
            }
        });





    }

    public void firstSong(View view) {
        String songID = String.valueOf(R.raw.bargainsinatuxedo);
        launchPlayer(songID);
    }


    public void playSong(View View) {
        String songID = String.valueOf(R.raw.letitrip);
        launchPlayer(songID);
    }

    public void launchPlayer(String songID) {
        Intent launchSongPlayer = new Intent(SongPicker.this, MainActivity.class);
        launchSongPlayer.putExtra("songMessage", songID);
        startActivity(launchSongPlayer);
    }
}

