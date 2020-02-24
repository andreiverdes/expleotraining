package com.andreiverdes.training.expleo.victrola.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.andreiverdes.training.expleo.victrola.R;

public class ForegroundMusicService extends MusicService {

    private static final String CHANNEL = "PLAY_MUSIC_CHANNEL";
    private static final int FOREGROUND_ID = 35467890;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(FOREGROUND_ID, buildForegroundNotification());
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification buildForegroundNotification() {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this, createChannelId());
        b.setOngoing(true)
                .setContentTitle("Playing music")
                .setContentText("Some song name...")
                .setSmallIcon(R.drawable.ic_play_circle_filled_black_24dp)
                .setTicker("Playing music!");

        return(b.build());
    }

    public String createChannelId() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL, "Music channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
                    .createNotificationChannel(channel);
        }
        return CHANNEL;
    }
}
