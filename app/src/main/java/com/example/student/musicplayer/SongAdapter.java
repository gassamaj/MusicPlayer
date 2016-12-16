package com.example.student.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<SongObject> {



    private Context context;
    private ArrayList<SongObject> values;

    public SongAdapter(Context context, ArrayList<SongObject> values){
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View songView, ViewGroup parent) {
        if (songView == null) {
            songView = LayoutInflater.from(getContext()).inflate( R.layout.song_list_item  , parent, false);
        }
        SongObject thisSong = getItem(position);
        TextView songNameViewVar= (TextView) songView.findViewById( R.id.songNameView );
        songNameViewVar.setText(  thisSong.title  );

        TextView ArtistViewVar= (TextView) songView.findViewById( R.id.songArtistView );
        songNameViewVar.setText(  thisSong.title  );
// Be sure to modify the artist as well!

        return songView;
// end of getView
    }


}

