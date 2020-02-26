package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;

import com.andreiverdes.training.expleo.cinema.data.StackOverflowClient;

public class App extends Application {

    private QuestionsRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        StackOverflowClient stackOverflowClient = StackOverflowClient.instance;
        repository = QuestionsRepository.getInstance(stackOverflowClient);
    }

}