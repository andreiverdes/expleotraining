
package com.andreiverdes.training.expleo.stackoverflow.model;

import androidx.room.ColumnInfo;

import java.util.Objects;

public class DbQuestionOwner {

    public int userId;
    public int qid;
    public int reputation;
    public String userType;
    public String profileImage;
    public String displayName;
    @ColumnInfo(name = "owner_link") public String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbQuestionOwner owner = (DbQuestionOwner) o;
        return userId == owner.userId &&
                qid == owner.qid &&
                reputation == owner.reputation &&
                Objects.equals(userType, owner.userType) &&
                Objects.equals(profileImage, owner.profileImage) &&
                Objects.equals(displayName, owner.displayName) &&
                Objects.equals(link, owner.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, qid, reputation, userType, profileImage, displayName, link);
    }
}
