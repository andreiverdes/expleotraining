package com.andreiverdes.training.expleo.stackoverflow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andreiverdes.training.expleo.arch.R;
import com.andreiverdes.training.expleo.arch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    //app:items="@{viewModel.items}"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.recycler.setAdapter(new QuestionsAdapter());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.fetchQuestions();
    }


}
