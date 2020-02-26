package com.andreiverdes.training.expleo.stackoverflow;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.idescout.sql.SqlScoutServer;

public class SqlScoutServerObserver implements LifecycleObserver {

    private Context context;
    private SqlScoutServer sqlScoutServer;

    public SqlScoutServerObserver(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        sqlScoutServer = SqlScoutServer.create(context, context.getPackageName());
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        sqlScoutServer.pause();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        sqlScoutServer.resume();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        sqlScoutServer.destroy();
    }
}
