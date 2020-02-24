package com.andreiverdes.training.expleo.victrola.threads;

import android.os.Looper;

public class LooperThread2 extends Thread {

    private Looper looper;

    public LooperThread2() {
        super(LooperThread2.class.getSimpleName());
        start();
    }

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        Looper.loop();
    }

    public void quit() {
        looper.quit();
    }
}
