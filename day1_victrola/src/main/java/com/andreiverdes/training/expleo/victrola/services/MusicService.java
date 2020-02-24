package com.andreiverdes.training.expleo.victrola.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY_PAUSE_MUSIC:
                    playPauseMusic();
                    break;
                case ACTION_NEXT_SONG:
                    nextSong();
                    break;
                case ACTION_PREVIOUS_SONG:
                    previousSong();
                    break;
                case ACTION_QUIT:
                    stopSelf();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + intent.getAction());
            }
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
