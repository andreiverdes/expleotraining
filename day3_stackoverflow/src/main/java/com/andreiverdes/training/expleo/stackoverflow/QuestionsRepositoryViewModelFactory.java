package com.andreiverdes.training.expleo.stackoverflow;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;

public class QuestionsRepositoryViewModelFactory
        extends ViewModelProvider.NewInstanceFactory {

    private DataSource dataSource;

    public QuestionsRepositoryViewModelFactory (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            return ((T) new MainViewModel(dataSource));
        }
        return super.create(modelClass);
    }
}
