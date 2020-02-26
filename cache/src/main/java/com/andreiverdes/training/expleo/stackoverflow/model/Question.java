
package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey public Integer questionId;
    public Boolean isAnswered;
    public Integer viewCount;
    public Integer acceptedAnswerId;
    public Integer answerCount;
    public Integer score;
    public Integer lastActivityDate;
    public Integer creationDate;
    public String link;
    public String title;

}
