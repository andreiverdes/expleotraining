package com.andreiverdes.training.expleo.victrola.services;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BoundMusicService extends MusicService {

    private Linker linker = new Linker(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return linker;
    }

    public static class Linker extends Binder {
        private BoundMusicService service;

        public Linker(BoundMusicService service) {
            this.service = service;
        }

        public BoundMusicService getService() {
            return service;
        }
    }
}
