package com.andreiverdes.training.expleo.victrola.handlers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.andreiverdes.training.expleo.victrola.Lessons;
import com.andreiverdes.training.expleo.victrola.R;

public class HandlersFragment extends Fragment {

    private SimpleThread simpleWorker = new SimpleThread();
    private LooperThread looperWorker = new LooperThread();
    private BestThread bestThread = new BestThread();

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            TextView textView = getView().findViewById(R.id.text_handlers);
            String newText = textView.getText().toString()
                    + "\n"
                    + msg.obj.toString();
            textView.setText(newText);
        }
    };
    private int numberOfTasks = 0;
    private TextView textView;
    private TextView button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_handlers, container, false);
        textView = root.findViewById(R.id.text_handlers);
        button = root.findViewById(R.id.new_task);

        button.setOnClickListener(view -> {
            putBestHandlerToWork();
        });

        Lessons.displayLesson1(textView);
        return root;
    }

    private void putBestHandlerToWork() {
        if (numberOfTasks == 0) {
            textView.setText("");
        }
        bestThread.handler.post(() -> {
            bestThread.sleepRandomly();
            Message message = Message.obtain();
            message.obj = "Task " + ++numberOfTasks + " done from " + Thread.currentThread().getName();
            handler.dispatchMessage(message);
        });
    }

    private void putLooperWorkerToWork() {
        if (numberOfTasks == 0) {
            textView.setText("");
        }
        looperWorker.handlerLazy.get().post(() -> {
            looperWorker.sleepRandomly();
            Message message = Message.obtain();
            message.obj = "Task " + ++numberOfTasks + " done from " + Thread.currentThread().getName();
            handler.dispatchMessage(message);
        });
    }

    private void putSimpleWorkerToWork() {
        if (numberOfTasks == 0) {
            textView.setText("");
        }
        simpleWorker.post(() -> {
            simpleWorker.sleepRandomly();
            Message message = Message.obtain();
            message.obj = "Task " + ++numberOfTasks + " done from " + Thread.currentThread().getName();
            handler.dispatchMessage(message);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleWorker.quit();
        looperWorker.quit();
    }
}