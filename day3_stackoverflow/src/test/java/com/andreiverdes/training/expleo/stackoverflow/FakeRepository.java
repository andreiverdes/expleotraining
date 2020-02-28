package com.andreiverdes.training.expleo.stackoverflow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;
import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeRepository implements DataSource {

    private List<AppQuestion> questions = buildAppQuestionsList();
    private MutableLiveData<List<AppQuestion>> questionsLiveData
            = new MutableLiveData<>();
    private MutableLiveData<List<AppQuestion>> filteredQuestionsLiveData
            = new MutableLiveData<>();

    @Override
    public LiveData<List<AppQuestion>> getQuestions() {
        questionsLiveData.setValue(questions);
        return questionsLiveData;
    }

    @Override
    public void saveQuestions(List<AppQuestion> questions) {
        this.questions = questions;
        questionsLiveData.setValue(questions);
    }

    @Override
    public LiveData<List<AppQuestion>> filter(String filter) {
        List<AppQuestion> result = questions.stream()
                .filter(it -> it.title.contains(filter))
                .collect(Collectors.toList());
        filteredQuestionsLiveData.setValue(result);
        return filteredQuestionsLiveData;
    }

    public List<AppQuestion> buildAppQuestionsList() {
        //TODO build ^
        return new ArrayList<>();
    }
}
