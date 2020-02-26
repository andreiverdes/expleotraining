package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;

import com.andreiverdes.training.expleo.cinema.data.StackoverflowClient;
import com.andreiverdes.training.expleo.stackoverflow.model.Translator;
import com.andreiverdes.training.expleo.stackoverflow.repository.CacheDataSource;
import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;
import com.andreiverdes.training.expleo.stackoverflow.repository.QuestionsRepository;
import com.andreiverdes.training.expleo.stackoverflow.repository.RemoteDataSource;

public class App extends Application {

    private QuestionsRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        StackoverflowClient stackOverflowClient = StackoverflowClient.instance;
        QuestionsDatabase questionsDatabase = QuestionsDatabase.getInstance(this);
        Translator translator = new Translator();
        DataSource cacheDataSource = new CacheDataSource(questionsDatabase, translator);
        DataSource remoteDataSource = new RemoteDataSource(stackOverflowClient, translator);
        repository = QuestionsRepository.getInstance(remoteDataSource, cacheDataSource);
    }

    public DataSource getDataSource() {
        return repository;
    }
}