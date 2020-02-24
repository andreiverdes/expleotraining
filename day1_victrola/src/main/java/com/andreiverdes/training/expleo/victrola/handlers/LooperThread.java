package com.andreiverdes.training.expleo.victrola.handlers;

import android.os.Looper;

import java.util.Random;


public class LooperThread extends Thread {

    public Looper looper;
    private Random random = new Random();

    public LooperThread() {
        super(LooperThread.class.getSimpleName());
        start();
    }

    @Override
    public void run() {
        Looper.prepare();
        this.looper = Looper.myLooper();
        Looper.loop();
    }

    public void quit() {
        looper.quit();
    }

    public void sleepRandomly() {
        try {
            Thread.sleep(random.nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
