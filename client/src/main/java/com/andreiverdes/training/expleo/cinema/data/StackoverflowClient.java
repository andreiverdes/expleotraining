package com.andreiverdes.training.expleo.cinema.data;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class StackoverflowClient {

    public static final StackoverflowClient instance = new StackoverflowClient();

    public final QuestionsApi questionsApi;

    private StackoverflowClient() {
        Moshi moshi = new Moshi.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        this.questionsApi = retrofit.create(QuestionsApi.class);
    }
}
