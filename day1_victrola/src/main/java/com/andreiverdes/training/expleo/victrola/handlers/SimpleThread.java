package com.andreiverdes.training.expleo.victrola.handlers;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleThread extends Thread {

    private AtomicBoolean alive = new AtomicBoolean(true);
    private ConcurrentLinkedQueue<Runnable> messages = new ConcurrentLinkedQueue<>();
    private Random random = new Random();

    public SimpleThread() {
        super(SimpleThread.class.getSimpleName());
        start();
    }

    @Override
    public void run() {
        while (alive.get()) {
            Runnable task = messages.poll();
            if (task != null) {
                task.run();
            }
        }
    }

    public void post(Runnable task) {
        this.messages.add(task);
    }

    public void quit() {
        this.alive.set(false);
    }

    public void sleepRandomly() {
        try {
            Thread.sleep(random.nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
