package com.andreiverdes.training.expleo.stackoverflow.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.andreiverdes.training.expleo.stackoverflow.data.StackoverflowClient;
import com.andreiverdes.training.expleo.stackoverflow.data.model.QuestionsList;
import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;
import com.andreiverdes.training.expleo.stackoverflow.Translator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {

    private final StackoverflowClient client;
    private final Translator translator;

    private MutableLiveData<List<AppQuestion>> questionsLiveData = new MutableLiveData<>();

    public RemoteDataSource(StackoverflowClient client, Translator translator) {
        this.client = client;
        this.translator = translator;
    }

    @Override
    public LiveData<List<AppQuestion>> getQuestions() {
        client.questionsApi.getMostRecentQuestions().enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    questionsLiveData.postValue(translator.clientToAppQuestions(response.body()));
                }
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                //TODO handle errors on a separate livedatas
            }
        });
        return questionsLiveData;
    }

    @Override
    public void saveQuestions(List<AppQuestion> questions) {
        throw new UnsupportedOperationException("Can't save questions in the Remote DatasSource");
    }

    @Override
    public LiveData<List<AppQuestion>> filter(String filter) {
        throw new UnsupportedOperationException("Not Implemented!");
    }
}
