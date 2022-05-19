package com.learning.musicplayer2;

import android.media.MediaPlayer;

public class Player {

    static MediaPlayer instance;

    public static MediaPlayer getInstance(){
        if(instance == null){
            instance = new MediaPlayer();
        }
        return instance;
    }

    public static int currentIndex = -1;
}
