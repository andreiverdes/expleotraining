package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<QuestionsAdapter.QuestionItem>> blablaLiveData
            = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<QuestionsAdapter.QuestionItem>> getItemsListLiveData() {
        return blablaLiveData;
    }

}
