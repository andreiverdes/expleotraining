package com.andreiverdes.training.expleo.stackoverflow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.andreiverdes.training.expleo.cinema.data.StackoverflowClient;
import com.andreiverdes.training.expleo.cinema.data.model.QuestionsList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsRepository {
    private static QuestionsRepository ourInstance;
    private StackoverflowClient stackOverflowClient;

    private MutableLiveData<QuestionsList> questionsListLiveData = new MutableLiveData<>();

    public static QuestionsRepository getInstance(
            StackoverflowClient stackOverflowClient
    ) {
        if (ourInstance == null) {
            ourInstance = new QuestionsRepository(stackOverflowClient);
        }
        return ourInstance;
    }

    private QuestionsRepository(StackoverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }

    public LiveData<QuestionsList> getQuestionsListLiveData() {
        return questionsListLiveData;
    }

    public void fetchQuestionsFromServer() {
        this.stackOverflowClient.questionsApi.getFirst50Questions()
                .enqueue(new Callback<QuestionsList>() {
                    @Override
                    public void onResponse(Call<QuestionsList> call,
                                           Response<QuestionsList> response) {
                        if (response.isSuccessful()) {
                            questionsListLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsList> call,
                                          Throwable t) {

                    }
                });
    }
}
