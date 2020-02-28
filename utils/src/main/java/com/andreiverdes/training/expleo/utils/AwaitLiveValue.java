package com.andreiverdes.training.expleo.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.junit.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class AwaitLiveValue {

    public static <T> T getOrAwait(LiveData<T> liveData) {
        return getOrAwait(liveData, 2, TimeUnit.SECONDS, () -> {});
    }
    @SuppressWarnings("UNCHECKED_CAST")
    public static <T> T getOrAwait(LiveData<T> liveData, long time, TimeUnit timeUnit,
                                   Runnable afterObserver) {
        AtomicReference<T> data = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T t) {
                data.set(t);
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        try {
            afterObserver.run();
            if (!latch.await(time, timeUnit)) {
                throw new TimeoutException("LiveData value was never set!");
            }
        } catch (TimeoutException | InterruptedException e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            liveData.removeObserver(observer);
        }
        return (T) data.get();
    }


}
