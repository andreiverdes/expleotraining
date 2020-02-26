package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.andreiverdes.training.expleo.cinema.data.model.Item;
import com.andreiverdes.training.expleo.cinema.data.model.QuestionsList;
import com.andreiverdes.training.expleo.stackoverflow.model.QuestionItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<QuestionItem>> blablaLiveData;
    private final QuestionsRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = ((App) application).getRepository();
        LiveData<QuestionsList> questionsListLiveData = repository.getQuestionsListLiveData();
        blablaLiveData = Transformations.map(questionsListLiveData, questionsList -> {
            List<QuestionItem> result = new ArrayList<>();
            for (Item item : questionsList.items) {
                QuestionItem questionItem = new QuestionItem();
                questionItem.photoUri = Uri.parse(item.owner.profileImage);
                questionItem.questionDate = getQuestionDateStringFormat(item);
                questionItem.questionTitle = item.title;
                result.add(questionItem);
            }
            return result;
        });
    }

    @NotNull
    private String getQuestionDateStringFormat(Item item) {
        return new Date(item.creationDate).toString();
    }

    public void fetchQuestions() {
        repository.fetchQuestionsFromServer();
    }

    public LiveData<List<QuestionItem>> getItemsListLiveData() {
        return blablaLiveData;
    }

}
