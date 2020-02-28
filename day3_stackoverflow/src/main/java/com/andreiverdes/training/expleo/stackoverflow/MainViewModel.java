package com.andreiverdes.training.expleo.stackoverflow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.andreiverdes.training.expleo.stackoverflow.repository.DataSource;

import java.util.List;

public class MainViewModel extends ViewModel {

    private Translator translator = new Translator();
    private MediatorLiveData<List<QuestionsAdapter.Item>> recyclerQuestionItemsLiveData = new MediatorLiveData<>();
    private MutableLiveData<String> searchString = new MutableLiveData<>();

    public MainViewModel(DataSource repository) {
        LiveData<List<QuestionsAdapter.Item>> allQuestions =
                Transformations.map(repository.getQuestions(), translator::appToRecyclerItem);
        LiveData<List<QuestionsAdapter.Item>> filtered = Transformations
                .switchMap(searchString, string ->
                        Transformations.map(repository.filter(string), translator::appToRecyclerItem));

        recyclerQuestionItemsLiveData.addSource(allQuestions, items -> recyclerQuestionItemsLiveData.setValue(items));
        recyclerQuestionItemsLiveData.addSource(filtered, items -> recyclerQuestionItemsLiveData.setValue(items));
    }

    public LiveData<List<QuestionsAdapter.Item>> getItemsListLiveData() {
        return recyclerQuestionItemsLiveData;
    }

    public MutableLiveData<String> getSearchString() {
        return searchString;
    }
}
