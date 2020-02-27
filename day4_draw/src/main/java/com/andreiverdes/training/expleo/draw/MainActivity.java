package com.andreiverdes.training.expleo.draw;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.andreiverdes.training.expleo.draw.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static int ROWS = 50;
    private static int COLUMNS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycler.setAdapter(new CirclesAdapter(ROWS, COLUMNS));
        binding.recycler.setLayoutManager(new GridLayoutManager(this,  COLUMNS));

    }
}
