package com.andreiverdes.training.expleo.cinema.ui.step4;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class CustomLifecycleComponent implements LifecycleObserver {

    private static final String TAG = "CustomLifecycleComponen";

    public CustomLifecycleComponent(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void thisIsCalledOnResumeEvent() {
        Log.i(TAG, "onResume()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void thisIsCalledOnPauseEvent() {
        Log.i(TAG, "onPause()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void thisIsCalledOnAnyEvent() {
        Log.i(TAG, "onEvent()");
    }

}
