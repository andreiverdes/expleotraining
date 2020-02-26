
package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.ColumnInfo;

public class DbQuestionOwner {

    public int userId;
    public int qid;
    public int reputation;
    public String userType;
    public String profileImage;
    public String displayName;
    @ColumnInfo(name = "owner_link") public String link;

}
