package com.andreiverdes.training.expleo.victrola.threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleThread2 extends Thread {

    AtomicBoolean alive = new AtomicBoolean(true);
    ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

    public SimpleThread2() {
        super(SimpleThread2.class.getSimpleName());
        start();
    }

    @Override public void run() {
        while (alive.get()) {
            Runnable task = queue.poll();
            if (task != null) {
                task.run();
            }
        }
    }

    public void post(Runnable task) {
        queue.add(task);
    }

    public void quit() {
        alive.set(false);
    }

}
