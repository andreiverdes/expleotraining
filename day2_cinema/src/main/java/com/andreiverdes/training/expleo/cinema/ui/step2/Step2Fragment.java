package com.andreiverdes.training.expleo.cinema.ui.step2;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andreiverdes.training.expleo.cinema.R;

public class Step2Fragment extends Fragment {

    private Step2ViewModel step2ViewModel;
    private Chronometer chronometer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        step2ViewModel = new ViewModelProvider(getActivity()).get(Step2ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_step2, container, false);
        chronometer = root.findViewById(R.id.time_text);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        step2ViewModel = new ViewModelProvider(getActivity()).get(Step2ViewModel.class);

        if (step2ViewModel.getStartTime() == 0) {
            long startTime = SystemClock.elapsedRealtime();
            step2ViewModel.setStartTime(startTime);
            chronometer.setBase(startTime);
        } else {
            chronometer.setBase(step2ViewModel.getStartTime());
        }
        chronometer.start();
    }
}