package com.andreiverdes.training.expleo.victrola.services;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.andreiverdes.training.expleo.victrola.Lessons;
import com.andreiverdes.training.expleo.victrola.R;

public class ServicesFragment extends Fragment {

    private TextView textView;
    private ImageButton playPause;
    private ImageButton next;
    private ImageButton previous;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_services, container, false);

        textView = root.findViewById(R.id.text_services);
        playPause = root.findViewById(R.id.play_pause);
        next = root.findViewById(R.id.next);
        previous = root.findViewById(R.id.previous);

        Lessons.displayLesson2(textView);


        playPause.setOnClickListener(v -> {

        });

        next.setOnClickListener(v -> {

        });

        previous.setOnClickListener(v -> {

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void startBackgroundService(String action) {
        Intent intent = new Intent(getContext(), BackgroundMusicService.class);
        intent.setAction(action);
        getContext().startService(intent);
    }

    private void startForegroundService(String action) {
        Intent intent = new Intent(getContext(), ForegroundMusicService.class);
        intent.setAction(action);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getContext().startForegroundService(intent);
        } else {
            getContext().startService(intent);
        }
    }
}