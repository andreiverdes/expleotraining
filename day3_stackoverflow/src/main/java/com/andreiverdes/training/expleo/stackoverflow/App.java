package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;

import com.andreiverdes.training.expleo.cinema.data.StackoverflowClient;

public class App extends Application {

    private QuestionsRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        StackoverflowClient stackOverflowClient = StackoverflowClient.instance;
        repository = QuestionsRepository.getInstance(stackOverflowClient);
    }

    public QuestionsRepository getRepository() {
        return repository;
    }
}