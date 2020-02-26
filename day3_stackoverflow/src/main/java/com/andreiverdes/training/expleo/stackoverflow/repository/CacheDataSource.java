package com.andreiverdes.training.expleo.stackoverflow.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.andreiverdes.training.expleo.stackoverflow.QuestionsDatabase;
import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;
import com.andreiverdes.training.expleo.stackoverflow.Translator;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CacheDataSource implements DataSource {

    private static final String TAG = "CacheDataSource";

    private final QuestionsDatabase database;
    private Translator translator;
    private Executor insertExecutor = Executors.newSingleThreadExecutor();

    public CacheDataSource(QuestionsDatabase database, Translator translator) {
        this.database = database;
        this.translator = translator;
    }

    @Override
    public LiveData<List<AppQuestion>> getQuestions() {
        LiveData<List<DbQuestion>> allQuestions = database.questionDao().getAllQuestions();
        return Transformations.map(allQuestions, dbQuestions -> translator.dbToAppQuestions(dbQuestions));
    }

    @Override
    public void saveQuestions(List<AppQuestion> questions) {
        insertExecutor.execute(() -> {
            try {
               database.questionDao().addAll(translator.appToDbQuestions(questions));
            } catch (Exception e) {
                Log.e(TAG, "saveQuestions: ", e);
            }
        });
    }

    @Override
    public LiveData<List<AppQuestion>> filter(String filter) {
        return Transformations.map(database.questionDao().filterTitles(filter),
                questions -> translator.dbToAppQuestions(questions));
    }
}
