package com.learning.musicplayer2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.learning.musicplayer2.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {

    TextView titleTextView,currentTimeTextView,durationTextView;
    SeekBar durationBar;
    ImageView pauseORplay,nextButton,previousButton,albumArt;
    ArrayList<SongFile> playList;
    SongFile currentSong;
    MediaPlayer mediaPlayer = Player.getInstance();
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        titleTextView = findViewById(R.id.song_title);
        currentTimeTextView = findViewById(R.id.current_time);
        durationTextView = findViewById(R.id.total_time);
        durationBar = findViewById(R.id.duration_bar);
        pauseORplay = findViewById(R.id.pause_play);
        nextButton = findViewById(R.id.next);
        previousButton = findViewById(R.id.previous);
        albumArt = findViewById(R.id.music_icon_big);

        titleTextView.setSelected(true);

        playList = (ArrayList<SongFile>) getIntent().getSerializableExtra("Playlist");

        musicResourceSetter();

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    durationBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTextView.setText(durationFormatting(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pauseORplay.setImageResource(R.drawable.ic_baseline_pause_24);
                        albumArt.setRotation(x++);
                    }else{
                        pauseORplay.setImageResource(R.drawable.ic_baseline_play_24);
                        albumArt.setRotation(0);
                    }

                }
                new Handler().postDelayed(this,100);
            }
        });

        durationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar durationBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar durationBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar durationBar) {

            }
        });


    }

    void musicResourceSetter(){
        currentSong = playList.get(Player.currentIndex);

        titleTextView.setText(currentSong.getSongTitle());

        durationTextView.setText(durationFormatting(currentSong.getDuration()));

        pauseORplay.setOnClickListener(v-> pauseORplay());
        nextButton.setOnClickListener(v-> nextSong());
        previousButton.setOnClickListener(v-> previousSong());

        playSong();


    }


    private void playSong(){

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            durationBar.setProgress(0);
            durationBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void nextSong(){

        if(Player.currentIndex== playList.size()-1)
            return;
        Player.currentIndex +=1;
        mediaPlayer.reset();
        musicResourceSetter();

    }

    private void previousSong(){
        if(Player.currentIndex== 0)
            return;
        Player.currentIndex -=1;
        mediaPlayer.reset();
        musicResourceSetter();
    }

    private void pauseORplay(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }


    public static String durationFormatting(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}