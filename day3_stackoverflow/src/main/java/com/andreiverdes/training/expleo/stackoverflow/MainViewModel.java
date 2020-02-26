package com.andreiverdes.training.expleo.stackoverflow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<QuestionsAdapter.QuestionItem>> blablaLiveData
            = new MutableLiveData<>();

    public LiveData<List<QuestionsAdapter.QuestionItem>> getItemsListLiveData() {
        return blablaLiveData;
    }

}
