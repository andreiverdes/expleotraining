package com.andreiverdes.training.expleo.arch.ui.step1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.andreiverdes.training.expleo.arch.R;

public class Step1Fragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step1, container, false);
        final Chronometer textView = root.findViewById(R.id.time_text);
        textView.start();
        return root;
    }
}