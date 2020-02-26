package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;

import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;
import com.andreiverdes.training.expleo.stackoverflow.repository.QuestionsRepository;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    private QuestionsRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        repository = QuestionsRepository.getInstance(this);
    }

    public DataSource getDataSource() {
        return repository;
    }
}