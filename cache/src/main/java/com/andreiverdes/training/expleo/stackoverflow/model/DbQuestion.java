
package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Objects;

@Entity(tableName = "questions")
public class DbQuestion {

    @PrimaryKey public int questionId;
    @Embedded public ArrayList<DbQuestionTag> tags;
    @Embedded public DbQuestionOwner owner;
    public Boolean isAnswered;
    public int viewCount;
    public int acceptedAnswerId;
    public int answerCount;
    public int score;
    public int lastActivityDate;
    public int creationDate;
    public String link;
    public String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbQuestion that = (DbQuestion) o;
        return questionId == that.questionId &&
                viewCount == that.viewCount &&
                acceptedAnswerId == that.acceptedAnswerId &&
                answerCount == that.answerCount &&
                score == that.score &&
                lastActivityDate == that.lastActivityDate &&
                creationDate == that.creationDate &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(isAnswered, that.isAnswered) &&
                Objects.equals(link, that.link) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, owner, isAnswered, viewCount, acceptedAnswerId, answerCount, score, lastActivityDate, creationDate, link, title);
    }
}
