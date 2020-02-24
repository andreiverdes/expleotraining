package com.andreiverdes.training.expleo.victrola.workmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.andreiverdes.training.expleo.victrola.R;

public class WorkManagerFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_work_manager, container, false);

        final TextView textView = root.findViewById(R.id.text_notifications);

        return root;
    }
}