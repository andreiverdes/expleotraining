package com.andreiverdes.training.expleo.stackoverflow.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions")
    LiveData<List<DbQuestion>> getAllQuestions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<DbQuestion> dbQuestions);

}
