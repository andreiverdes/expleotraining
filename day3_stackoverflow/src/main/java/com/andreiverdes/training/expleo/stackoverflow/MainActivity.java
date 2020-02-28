package com.andreiverdes.training.expleo.stackoverflow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andreiverdes.training.expleo.arch.R;
import com.andreiverdes.training.expleo.arch.databinding.ActivityMainBinding;
import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;
import com.andreiverdes.training.expleo.utils.Lazy;

import static com.andreiverdes.training.expleo.utils.Lazy.*;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private SqlScoutServerObserver sqlScoutServerObserver = new SqlScoutServerObserver(this);

    private Lazy<DataSource> dataSourceLazy
            = byLazy(() -> ((App) getApplicationContext()).getDataSource());
    private Lazy<QuestionsRepositoryViewModelFactory> viewModelFactoryLazy
            = byLazy(() -> new QuestionsRepositoryViewModelFactory(dataSourceLazy.get()));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getLifecycle().addObserver(sqlScoutServerObserver);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this, viewModelFactoryLazy.get()).get(MainViewModel.class);
        binding.recycler.setAdapter(new QuestionsAdapter());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }


}
