package com.andreiverdes.training.expleo.victrola.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SleeperWorker extends Worker {

    public static final String TAG = SleeperWorker.class.getSimpleName();
    public static final String KEY_PROGRESS = "key_progress";

    public SleeperWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "SleeperWorker went to sleep");
        try {
            Thread.sleep(1000);
            setProgressAsync(new Data.Builder().putInt(KEY_PROGRESS, 33).build());
            Thread.sleep(1000);
            setProgressAsync(new Data.Builder().putInt(KEY_PROGRESS, 66).build());
            Thread.sleep(1000);
            Log.i(TAG, "SleeperWorker hat a great nap!");
            setProgressAsync(new Data.Builder().putInt(KEY_PROGRESS, 100).build());
            return Result.success();
        } catch (InterruptedException e) {
            Log.e(TAG, "SleeperWorker had a nightmare!", e);
            if (getRunAttemptCount() > 3) {
                return Result.failure();
            } else {
                return Result.retry();
            }
        }
    }
}
