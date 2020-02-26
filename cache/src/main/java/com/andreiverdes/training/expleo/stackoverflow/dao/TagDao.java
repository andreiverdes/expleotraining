package com.andreiverdes.training.expleo.stackoverflow.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.andreiverdes.training.expleo.stackoverflow.model.Tag;

import java.util.List;

@Dao
public interface TagDao {

    @Query("SELECT * FROM tags WHERE questionId = :questionId")
    LiveData<List<Tag>> getTagsForQuestion(String questionId);

}
