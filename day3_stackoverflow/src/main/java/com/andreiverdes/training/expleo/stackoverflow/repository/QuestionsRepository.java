package com.andreiverdes.training.expleo.stackoverflow.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;

import java.util.List;

public class QuestionsRepository implements DataSource {
    private static QuestionsRepository ourInstance;
    private final DataSource remoteDataSource;
    private final DataSource cacheDataSource;

    private MediatorLiveData<List<AppQuestion>> questionsListLiveData = new MediatorLiveData<>();

    public static QuestionsRepository getInstance(
            DataSource remoteDataSource,
            DataSource cacheDataSource
    ) {
        if (ourInstance == null) {
            ourInstance = new QuestionsRepository(remoteDataSource, cacheDataSource);
        }
        return ourInstance;
    }

    private QuestionsRepository(DataSource remoteDataSource, DataSource cacheDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.cacheDataSource = cacheDataSource;
        this.questionsListLiveData.addSource(cacheDataSource.getQuestions(), questions -> questionsListLiveData.setValue(questions));
        this.questionsListLiveData.addSource(remoteDataSource.getQuestions(), cacheDataSource::saveQuestions);
    }

    public void fetchQuestionsFromServer() {
        cacheDataSource.getQuestions();
        remoteDataSource.getQuestions();
    }

    @Override
    public LiveData<List<AppQuestion>> getQuestions() {
        return questionsListLiveData;
    }

    @Override
    public void saveQuestions(List<AppQuestion> questions) {
        cacheDataSource.saveQuestions(questions);
    }
}
