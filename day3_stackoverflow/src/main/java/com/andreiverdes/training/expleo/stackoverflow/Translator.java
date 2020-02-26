package com.andreiverdes.training.expleo.stackoverflow;

import android.net.Uri;

import com.andreiverdes.training.expleo.cinema.data.model.Item;
import com.andreiverdes.training.expleo.cinema.data.model.Owner;
import com.andreiverdes.training.expleo.cinema.data.model.QuestionsList;
import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestion;
import com.andreiverdes.training.expleo.stackoverflow.model.AppQuestionOwner;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestionOwner;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestionTag;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Translator {


    public List<AppQuestion> dbToAppQuestions(List<DbQuestion> dbQuestions) {
        List<AppQuestion> result = new ArrayList<>();
        for (DbQuestion dbQuestion : dbQuestions) {
            AppQuestion appQuestion = new AppQuestion();
            appQuestion.tags = dbToAppTags(dbQuestion.tags);
            appQuestion.appQuestionOwner = dbToApp(dbQuestion.owner);
            appQuestion.isAnswered = dbQuestion.isAnswered;
            appQuestion.viewCount = dbQuestion.viewCount;
            appQuestion.acceptedAnswerId = dbQuestion.acceptedAnswerId;
            appQuestion.answerCount = dbQuestion.answerCount;
            appQuestion.score = dbQuestion.score;
            appQuestion.lastActivityDate = dbQuestion.lastActivityDate;
            appQuestion.creationDate = dbQuestion.creationDate;
            appQuestion.questionId = dbQuestion.questionId;
            appQuestion.link = dbQuestion.link;
            appQuestion.title = dbQuestion.title;
            result.add(appQuestion);
        }
        return result;
    }

    private List<String> dbToAppTags(List<DbQuestionTag> tags) {
        List<String> result = new ArrayList<>();
        if (tags != null) {
            for (DbQuestionTag tag : tags) {
                result.add(tag.value);
            }
        }
        return result;
    }

    private AppQuestionOwner dbToApp(DbQuestionOwner owner) {
        AppQuestionOwner result = new AppQuestionOwner();
        result.reputation = owner.reputation;
        result.userId = owner.userId;
        result.userType = owner.userType;
        result.profileImage = owner.profileImage;
        result.displayName = owner.displayName;
        result.link = owner.link;
        return result;
    }

    public List<DbQuestion> appToDbQuestions(List<AppQuestion> questions) {
        List<DbQuestion> result = new ArrayList<>();
        for (AppQuestion question : questions) {
            DbQuestion dbQuestion = new DbQuestion();
            dbQuestion.tags = appToDbTags(question.questionId, question.tags);
            dbQuestion.owner = appToDbOwner(question.questionId, question.appQuestionOwner);
            dbQuestion.isAnswered = question.isAnswered;
            dbQuestion.viewCount = question.viewCount;
            dbQuestion.acceptedAnswerId = question.acceptedAnswerId;
            dbQuestion.answerCount = question.answerCount;
            dbQuestion.score = question.score;
            dbQuestion.lastActivityDate = question.lastActivityDate;
            dbQuestion.creationDate = question.creationDate;
            dbQuestion.questionId = question.questionId;
            dbQuestion.link = question.link;
            dbQuestion.title = question.title;
            result.add(dbQuestion);
        }
        return result;
    }

    private DbQuestionOwner appToDbOwner(int questionId, AppQuestionOwner appQuestionOwner) {
        DbQuestionOwner result = new DbQuestionOwner();
        result.qid = questionId;
        result.reputation = appQuestionOwner.reputation;
        result.userId = appQuestionOwner.userId;
        result.userType = appQuestionOwner.userType;
        result.profileImage = appQuestionOwner.profileImage;
        result.displayName = appQuestionOwner.displayName;
        result.link = appQuestionOwner.link;
        return result;
    }

    private ArrayList<DbQuestionTag> appToDbTags(int questionId, List<String> tags) {
        ArrayList<DbQuestionTag> result = new ArrayList<>();
        for (String tag : tags) {
            DbQuestionTag dbQuestionTag = new DbQuestionTag();
            dbQuestionTag.questionId = questionId;
            dbQuestionTag.value = tag;
            result.add(dbQuestionTag);
        }
        return result;
    }

    public List<AppQuestion> clientToAppQuestions(QuestionsList questionsList) {
        List<AppQuestion> result = new ArrayList<>();
        for (Item item : questionsList.items) {
            AppQuestion appQuestion = new AppQuestion();
            appQuestion.tags = item.tags;
            appQuestion.appQuestionOwner = clientToAppOwner(item.owner);
            appQuestion.isAnswered = item.isAnswered;
            appQuestion.viewCount = item.viewCount == null ? -1 : item.viewCount;
            appQuestion.acceptedAnswerId = item.acceptedAnswerId == null ? -1 : item.acceptedAnswerId;
            appQuestion.answerCount = item.answerCount == null ? -1 : item.answerCount;
            appQuestion.score = item.score == null ? -1 : item.score;
            appQuestion.lastActivityDate = item.lastActivityDate == null ? -1 : item.lastActivityDate;
            appQuestion.creationDate = item.creationDate == null ? -1 : item.creationDate;
            appQuestion.questionId = item.questionId == null ? -1 : item.questionId;
            appQuestion.link = item.link;
            appQuestion.title = item.title;
            result.add(appQuestion);
        }
        return result;
    }

    private AppQuestionOwner clientToAppOwner(Owner owner) {
        AppQuestionOwner result = new AppQuestionOwner();
        result.reputation = owner.reputation == null ? -1 : owner.reputation;
        result.userId = owner.userId == null ? -1 : owner.userId;
        result.userType = owner.userType;
        result.profileImage = owner.profileImage;
        result.displayName = owner.displayName;
        result.link = owner.link;
        return result;
    }

    @NotNull
    public List<QuestionsAdapter.Item> appToRecyclerItem(List<AppQuestion> questionsList) {
        List<QuestionsAdapter.Item> result = new ArrayList<>();
        for (AppQuestion appQuestion : questionsList) {
            QuestionsAdapter.Item questionItem = new QuestionsAdapter.Item();
            questionItem.photoUri = Uri.parse(appQuestion.appQuestionOwner.profileImage);
            questionItem.questionDate = getQuestionDateStringFormat(appQuestion.creationDate);
            questionItem.questionTitle = appQuestion.title;
            result.add(questionItem);
        }
        return result;
    }

    @NotNull
    private String getQuestionDateStringFormat(int time) {
        return new Date(time).toString();
    }
}
