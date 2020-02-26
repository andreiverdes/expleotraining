package com.andreiverdes.training.expleo.stackoverflow.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions")
    LiveData<List<Question>> getAllQuestions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<Question> questions);

}
