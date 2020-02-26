package com.andreiverdes.training.expleo.arch.ui.step2;

import androidx.lifecycle.ViewModel;

public class Step2ViewModel extends ViewModel {

    private long startTime = 0;


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}