package com.andreiverdes.training.expleo.victrola.handlers;

import android.os.HandlerThread;

import java.util.Random;

public class BestThread extends HandlerThread {


    private Random random = new Random();

    public BestThread() {
        super(BestThread.class.getSimpleName());
        start();
    }

    public void sleepRandomly() {
        try {
            Thread.sleep(random.nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
