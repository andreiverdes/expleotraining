
package com.andreiverdes.training.expleo.cinema.data.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Item {

    @Json(name = "tags")  public List<String> tags = null;
    @Json(name = "owner")  public Owner owner;
    @Json(name = "is_answered")  public Boolean isAnswered;
    @Json(name = "view_count")  public Integer viewCount;
    @Json(name = "accepted_answer_id")  public Integer acceptedAnswerId;
    @Json(name = "answer_count")  public Integer answerCount;
    @Json(name = "score")  public Integer score;
    @Json(name = "last_activity_date")  public Integer lastActivityDate;
    @Json(name = "creation_date")  public Integer creationDate;
    @Json(name = "question_id")  public Integer questionId;
    @Json(name = "link")  public String link;
    @Json(name = "title")  public String title;

    public String getDateString() {
        return new Date(creationDate).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(tags, item.tags) &&
                Objects.equals(owner, item.owner) &&
                Objects.equals(isAnswered, item.isAnswered) &&
                Objects.equals(viewCount, item.viewCount) &&
                Objects.equals(acceptedAnswerId, item.acceptedAnswerId) &&
                Objects.equals(answerCount, item.answerCount) &&
                Objects.equals(score, item.score) &&
                Objects.equals(lastActivityDate, item.lastActivityDate) &&
                Objects.equals(creationDate, item.creationDate) &&
                Objects.equals(questionId, item.questionId) &&
                Objects.equals(link, item.link) &&
                Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags, owner, isAnswered, viewCount, acceptedAnswerId,
                answerCount, score, lastActivityDate, creationDate, questionId, link, title);
    }
}
