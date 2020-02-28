package com.andreiverdes.training.expleo.stackoverflow.data;

import com.andreiverdes.training.expleo.stackoverflow.data.model.Item;
import com.andreiverdes.training.expleo.stackoverflow.data.model.QuestionsList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class QuestionsApiTest {

    FileUtils fileUtils = new FileUtils();
    MockWebServer mockWebServer = new MockWebServer();
    private QuestionsApi questionsApi;

    @Before
    public void setup() {
        HttpUrl url = mockWebServer.url("/");
        questionsApi = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(QuestionsApi.class);
    }

    @After
    public void teardown() {

    }

    /**
     * subjectUnderTest_actionOrInput_resultState
     */
    @Test
    public void getFirst50Questions() throws IOException {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(fileUtils.getQuestionsJsonString("questions.json"));

        mockWebServer.enqueue(mockResponse);

        Response<QuestionsList> response = questionsApi.getMostRecentQuestions()
                .execute();

        QuestionsList body = response.body();
        String actualTitle = "Android subscription localisation";
        int actualId = 60446941;
        Optional<Item> itemIdSearch = body.items.stream()
                .filter(item -> item.questionId == actualId)
                .findFirst();
        Optional<Item> itemTitleSearch = body.items.stream()
                .filter(item -> item.title.equals(actualTitle))
                .findFirst();
        assertEquals(body.items.size(), 30);
        assertNotNull(itemIdSearch.get());
        assertNotNull(itemTitleSearch.get());
    }



    @Test
    public void getQuestions() throws IOException {
        MockResponse pageOneResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(fileUtils.getQuestionsJsonString("questions_page1.json"));

        MockResponse pageTwoResponse = new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(fileUtils.getQuestionsJsonString("questions_page2.json"));

        mockWebServer.enqueue(pageOneResponse);
        mockWebServer.enqueue(pageTwoResponse);

        Response<QuestionsList> response1 = questionsApi.getQuestions(0, 0)
                .execute();

        Response<QuestionsList> response2 = questionsApi.getQuestions(0, 0)
                .execute();

        QuestionsList body = response1.body();
        String actualTitle = "Create array of object in android retrofit2 using put request";
        int actualId = 60447107;
        Optional<Item> itemIdSearch = body.items.stream()
                .filter(item -> item.questionId == actualId)
                .findFirst();
        Optional<Item> itemTitleSearch = body.items.stream()
                .filter(item -> item.title.equals(actualTitle))
                .findFirst();
        assertEquals(body.items.size(), 24);
        assertNotNull(itemIdSearch.get());
        assertNotNull(itemTitleSearch.get());
        assertEquals(response1.code(), HttpURLConnection.HTTP_OK);

    }
}