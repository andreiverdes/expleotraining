package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;
import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<QuestionsAdapter.Item>> recyclerQuestionItemsLiveData;
    private final DataSource repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = ((App) application).getDataSource();
        recyclerQuestionItemsLiveData = Transformations.map(repository.getQuestions(), questionsList -> {
            List<QuestionsAdapter.Item> result = new ArrayList<>();
            for (AppQuestion appQuestion : questionsList) {
                QuestionsAdapter.Item questionItem = new QuestionsAdapter.Item();
                questionItem.photoUri = Uri.parse(appQuestion.appQuestionOwner.profileImage);
                questionItem.questionDate = getQuestionDateStringFormat(appQuestion.creationDate);
                questionItem.questionTitle = appQuestion.title;
                result.add(questionItem);
            }
            return result;
        });
    }

    @NotNull
    private String getQuestionDateStringFormat(int time) {
        return new Date(time).toString();
    }

    public LiveData<List<QuestionsAdapter.Item>> getItemsListLiveData() {
        return recyclerQuestionItemsLiveData;
    }

}
