package com.andreiverdes.training.expleo.victrola.services;

import android.app.Service;
import android.media.MediaPlayer;

import com.andreiverdes.training.expleo.victrola.R;

import java.util.ArrayList;
import java.util.List;

public abstract class MusicService extends Service {

    public static final String ACTION_PLAY_PAUSE_MUSIC = "ACTION_PLAY_MUSIC";
    public static final String ACTION_NEXT_SONG = "ACTION_NEXT_SONG";
    public static final String ACTION_PREVIOUS_SONG = "ACTION_PREVIOUS_SONG";
    public static final String ACTION_QUIT = "ACTION_QUIT";

    private MediaPlayer mediaPlayer;
    private List<Integer> songs = new ArrayList<>();
    private int currentSongIndex = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        songs.add(R.raw.bensound_summer);
        songs.add(R.raw.bensound_ukulele);
        songs.add(R.raw.bensound_creativeminds);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void previousSong() {
        mediaPlayer.stop();
        mediaPlayer.release();
        currentSongIndex = ++currentSongIndex % songs.size();
        mediaPlayer = MediaPlayer.create(this, songs.get(currentSongIndex));
        mediaPlayer.start();
    }

    public void nextSong() {
        mediaPlayer.stop();
        mediaPlayer.release();
        currentSongIndex = ++currentSongIndex % songs.size();
        mediaPlayer = MediaPlayer.create(this, songs.get(currentSongIndex));
        mediaPlayer.start();
    }

    public void playPauseMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, songs.get(currentSongIndex));
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }
}
