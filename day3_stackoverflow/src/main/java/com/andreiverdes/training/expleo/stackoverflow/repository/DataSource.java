package com.andreiverdes.training.expleo.stackoverflow.repository;

import androidx.lifecycle.LiveData;

import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;

import java.util.List;

public interface DataSource {

    LiveData<List<AppQuestion>> getQuestions();

    void saveQuestions(List<AppQuestion> questions);

}
