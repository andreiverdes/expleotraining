
package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

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

}
