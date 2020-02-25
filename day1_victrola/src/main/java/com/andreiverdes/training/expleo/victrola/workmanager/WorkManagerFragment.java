package com.andreiverdes.training.expleo.victrola.workmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.andreiverdes.training.expleo.victrola.R;
import com.andreiverdes.training.expleo.victrola.util.Lazy;

import java.util.concurrent.TimeUnit;

public class WorkManagerFragment extends Fragment {

    private static final String TAG = SleeperWorker.TAG;

    public static final String KEY_INPUT = "key_input";
    private TextView textView;
    private Lazy<WorkManager> workManagerLazy = Lazy.byLazy(() -> WorkManager.getInstance(getContext()));

    private Constraints sleepConstraints = new Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresDeviceIdle(false)
            .setRequiresStorageNotLow(false)
            .build();

    private Data sleepInputData = new Data.Builder()
            .putString(KEY_INPUT, "Sweet Dreams!")
            .build();

    private OneTimeWorkRequest sleepRequest = new OneTimeWorkRequest.Builder(SleeperWorker.class)
            .setConstraints(sleepConstraints)
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .setInputData(sleepInputData)
            .addTag("someTag")
            .build();

    private OneTimeWorkRequest secondSleepRequest = new OneTimeWorkRequest.Builder(SleeperWorker.class)
            .setConstraints(sleepConstraints)
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_work_manager, container, false);

        textView = root.findViewById(R.id.text_work_manager);


        workManagerLazy.get()
                .beginWith(sleepRequest)
                .then(secondSleepRequest)
                .enqueue();

        LiveData<WorkInfo> infoLiveData1 = workManagerLazy.get().getWorkInfoByIdLiveData(sleepRequest.getId());
        LiveData<WorkInfo> infoLiveData2 = workManagerLazy.get().getWorkInfoByIdLiveData(secondSleepRequest.getId());

        MediatorLiveData<WorkInfo> mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.addSource(infoLiveData1, mediatorLiveData::setValue);
        mediatorLiveData.addSource(infoLiveData2, mediatorLiveData::setValue);

        mediatorLiveData.observe(getViewLifecycleOwner(), workInfo -> {
                    if (workInfo != null) {
                        int progress = workInfo.getProgress().getInt(SleeperWorker.KEY_PROGRESS, 0);
                        switch (workInfo.getState()) {
                            case ENQUEUED:
                                Log.i(TAG, "Request State: ENQUEUED");
                                break;
                            case RUNNING:
                                Log.i(TAG, "Request State: RUNNING: " + progress);
                                break;
                            case SUCCEEDED:
                                Log.i(TAG, "Request State: SUCCEEDED " + progress);
                                break;
                            case FAILED:
                                Log.i(TAG, "Request State: FAILED");
                                break;
                            case BLOCKED:
                                Log.i(TAG, "Request State: BLOCKED");
                                break;
                            case CANCELLED:
                                Log.i(TAG, "Request State: CANCELLED");
                                break;
                        }
                    }
                });


        return root;
    }
}