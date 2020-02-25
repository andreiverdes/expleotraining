package com.andreiverdes.training.expleo.cinema.ui.step3;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class Step3ViewModel extends ViewModel {

    private MutableLiveData<Long> timeLiveData = new MutableLiveData<>();

    private long startTime;
    private Timer timer = new Timer();

    public Step3ViewModel() {
        this.startTime = SystemClock.elapsedRealtime();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long time = (SystemClock.elapsedRealtime() - startTime) / 1000;
                timeLiveData.postValue(time);
            }
        }, 1000, 1000);
    }

    public LiveData<Long> getTimeLiveData() {
        return timeLiveData;
    }
}
