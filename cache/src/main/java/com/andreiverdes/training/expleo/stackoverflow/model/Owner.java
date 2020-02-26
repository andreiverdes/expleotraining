
package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "owners",
        foreignKeys = {@ForeignKey(
                entity = Question.class,
                parentColumns = "questionId",
                childColumns = "questionId",
                onDelete = ForeignKey.CASCADE

        )},
        indices = {@Index(value = "questionId")}
)
public class Owner {

    @PrimaryKey
    public Integer userId;
    public Integer questionId;
    public Integer reputation;
    public String userType;
    public String profileImage;
    public String displayName;
    public String link;

}
