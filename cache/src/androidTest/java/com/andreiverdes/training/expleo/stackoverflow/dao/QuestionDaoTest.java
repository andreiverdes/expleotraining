package com.andreiverdes.training.expleo.stackoverflow.dao;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.andreiverdes.training.expleo.stackoverflow.QuestionsDatabase;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestionOwner;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestionTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(AndroidJUnit4.class)
public class QuestionDaoTest {

    private QuestionDao questionDao;
    private QuestionsDatabase questionsDatabase;

    private List<DbQuestion> dummyQuestions = buildDummyQuestionsList();

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        questionsDatabase = Room.inMemoryDatabaseBuilder(context, QuestionsDatabase.class)
                .build();
        questionDao = questionsDatabase.questionDao();
    }

    @After
    public void tearDown() throws Exception {
        questionsDatabase.close();
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
        DbQuestionOwner owner = new DbQuestionOwner();
        {
            owner.userId = 12;
            owner.qid = 1234556;
            owner.reputation = 234;
            owner.userType = "some_type";
            owner.profileImage = "https://example.com/blabla.jpg";
            owner.displayName = "Johny Tester";
            owner.link = "https://testingislife.com";
        }
        DbQuestion dbQuestion = new DbQuestion();
        {
            List<DbQuestionTag> tags = IntStream.of(0, 1, 2)
                    .mapToObj(tag -> {
                        DbQuestionTag resultTag = new DbQuestionTag();
                        resultTag.questionId = tag;
                        resultTag.value = "tag" + tag;
                        return resultTag;
                    })
                    .collect(Collectors.toList());
            dbQuestion.tags = new ArrayList<>(tags);
            dbQuestion.questionId = owner.qid;
            dbQuestion.owner = owner;
            dbQuestion.isAnswered = true;
            dbQuestion.viewCount = 200;
            dbQuestion.acceptedAnswerId = 23334;
            dbQuestion.answerCount = 24;
            dbQuestion.score = 12435;
            dbQuestion.lastActivityDate = 329853;
            dbQuestion.creationDate = 328320;
            dbQuestion.link = "https://example.com";
            dbQuestion.title = "Very Important Question!";
        }
        return result;
    }
}