package com.andreiverdes.training.expleo.stackoverflow.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.andreiverdes.training.expleo.stackoverflow.model.Owner;

@Dao
public interface OwnerDao {

    @Query("SELECT * FROM owners WHERE questionId = :questionId")
    LiveData<Owner> getOwnerForQuestion(String questionId);

}
