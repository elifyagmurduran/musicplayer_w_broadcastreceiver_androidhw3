package com.learning.musicplayer2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {
    ArrayList<SongFile> playList;
    Context context;

    public LibraryAdapter(ArrayList<SongFile> playList, Context context) {
        this.playList = playList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout_resource,parent,false);
        return new LibraryAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        SongFile songData = playList.get(position);
        holder.musictitleTextView.setText(songData.getSongTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player.getInstance().reset();
                Player.currentIndex = position;
                Intent intent = new Intent(context,MusicPlayerActivity.class);
                intent.putExtra("Playlist",playList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView musictitleTextView;
        ImageView albumartImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            musictitleTextView = itemView.findViewById(R.id.music_title);
            albumartImageView = itemView.findViewById(R.id.album_art);
        }
    }
}
