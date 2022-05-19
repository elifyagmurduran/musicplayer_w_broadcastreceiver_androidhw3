package com.learning.musicplayer2;

public class SongFile {

    String filePath;
    String songTitle;
    String duration;

    public SongFile(String filePath, String songTitle, String duration) {
        this.filePath = filePath;
        this.songTitle = songTitle;
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
