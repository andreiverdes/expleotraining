package com.andreiverdes.training.expleo.arch.ui.step3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andreiverdes.training.expleo.arch.R;

public class Step3Fragment extends Fragment {

    private Step3ViewModel notificationsViewModel;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step3, container, false);
        textView = root.findViewById(R.id.time_text);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notificationsViewModel = new ViewModelProvider(getActivity()).get(Step3ViewModel.class);

        notificationsViewModel.getTimeLiveData().observe(getViewLifecycleOwner(), time -> {
            textView.setText("" + time + " seconds since started");
        });

    }
}