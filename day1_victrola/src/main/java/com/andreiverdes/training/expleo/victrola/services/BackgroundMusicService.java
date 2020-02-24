package com.andreiverdes.training.expleo.victrola.services;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundMusicService extends MusicService {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
