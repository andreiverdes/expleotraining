package com.andreiverdes.training.expleo.stackoverflow;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.andreiverdes.training.expleo.stackoverflow.dao.QuestionDao;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestionOwner;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestionTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.andreiverdes.training.expleo.utils.AwaitLiveValue.getOrAwait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class QuestionDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private QuestionDao questionDao;
    private QuestionsDatabase questionsDatabase;

    private List<DbQuestion> dummyQuestions = buildDummyQuestionsList();

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        questionsDatabase = Room.inMemoryDatabaseBuilder(context, QuestionsDatabase.class)
                .allowMainThreadQueries()
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
        questionDao.addAll(dummyQuestions);
        LiveData<List<DbQuestion>> allQuestions = questionDao.getAllQuestions();
        List<DbQuestion> resultQuestions = getOrAwait(allQuestions);

        assertEquals(dummyQuestions.get(0), resultQuestions.get(0));
    }

    @Test
    public void filterTitles() {
        questionDao.addAll(dummyQuestions);
        LiveData<List<DbQuestion>> filteredQuestions
                = questionDao.filterTitles("Important");
        List<DbQuestion> resultQuestions = getOrAwait(filteredQuestions);

        for (DbQuestion resultQuestion : resultQuestions) {
            assertTrue(resultQuestion.title.contains("Important"));
        }

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
        result.add(dbQuestion);
        return result;
    }
}