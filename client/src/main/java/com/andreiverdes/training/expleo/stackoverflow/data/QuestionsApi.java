package com.andreiverdes.training.expleo.stackoverflow.data;

import com.andreiverdes.training.expleo.stackoverflow.data.model.QuestionsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionsApi {

    @GET("/2.2/questions?page=1&pagesize=50&order=desc&sort=activity&tagged=android&site=stackoverflow")
    Call<QuestionsList> getMostRecentQuestions();

    @GET("/2.2/questions?order=desc&sort=activity&tagged=android&site=stackoverflow")
    Call<QuestionsList> getQuestions(@Query("page") int page, @Query("pagesize") int pageSize);

}
