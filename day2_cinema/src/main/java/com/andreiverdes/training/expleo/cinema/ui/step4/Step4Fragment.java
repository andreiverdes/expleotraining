package com.andreiverdes.training.expleo.cinema.ui.step4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andreiverdes.training.expleo.cinema.R;

public class Step4Fragment extends Fragment {


    private TextView textView;
    private CustomLifecycleComponent customLifecycleComponent;

    private SharedViewModel sharedViewModel;
    private SeekBar seekBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step4, container, false);
        textView = root.findViewById(R.id.time_text);
        seekBar = root.findViewById(R.id.seekbar4);
        customLifecycleComponent = new CustomLifecycleComponent(getViewLifecycleOwner());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        this.sharedViewModel.getSeekBarValue().observe(getViewLifecycleOwner(), progress -> {
            seekBar.setProgress(progress);
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sharedViewModel.setSeekBarValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
