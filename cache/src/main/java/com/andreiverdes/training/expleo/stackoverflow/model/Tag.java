package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "tags",
        foreignKeys = {@ForeignKey(
                entity = Question.class,
                parentColumns = "questionId",
                childColumns = "questionId"
        )},
        indices = {@Index(value = "questionId")}
)
public class Tag {
        @PrimaryKey(autoGenerate = true)
        public int id;
        public Integer questionId;
        public String value;
}
