package com.andreiverdes.training.expleo.stackoverflow.dao;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.andreiverdes.training.expleo.stackoverflow.QuestionsDatabase;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class QuestionDaoTest {

    private QuestionDao questionDao;
    private QuestionsDatabase db;

    private List<DbQuestion> dummyQuestions = buildDummyQuestionsList();

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, QuestionsDatabase.class).build();
        questionDao = db.questionDao();
        questionDao.addAll(dummyQuestions);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    /**
     * given_when_then
     * subjectUnderTest_actionOrInput_resultState
     */
    @Test
    public void getAllQuestions() {
        //TODO
    }

    @Test
    public void addAll() {
        //TODO
    }

    @Test
    public void filterTitles() {
        //TODO
    }

    private List<DbQuestion> buildDummyQuestionsList() {
        List<DbQuestion> result = new ArrayList<>();
        //TODO populate db
        return result;
    }
}