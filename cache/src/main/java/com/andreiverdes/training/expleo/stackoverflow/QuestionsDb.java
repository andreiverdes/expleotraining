package com.andreiverdes.training.expleo.stackoverflow;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.andreiverdes.training.expleo.stackoverflow.dao.OwnerDao;
import com.andreiverdes.training.expleo.stackoverflow.dao.QuestionDao;
import com.andreiverdes.training.expleo.stackoverflow.dao.TagDao;
import com.andreiverdes.training.expleo.stackoverflow.model.Owner;
import com.andreiverdes.training.expleo.stackoverflow.model.Question;
import com.andreiverdes.training.expleo.stackoverflow.model.Tag;

@Database(entities = {
        Question.class,
        Owner.class,
        Tag.class
}, version = 1)
public abstract class QuestionsDb extends RoomDatabase {

    private static QuestionsDb instance;

    public abstract OwnerDao ownerDao();

    public abstract QuestionDao questionDao();

    public abstract TagDao tagDao();

    private MutableLiveData<Boolean> databaseCreated = new MutableLiveData<>();

    public static QuestionsDb getInstance(Context context) {
        if (instance == null) {
            synchronized (QuestionsDb.class) {
                if (instance == null) {
                    instance = buildDatabase(context);
                }
            }
        }
        return instance;
    }

    private static QuestionsDb buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, QuestionsDb.class, "VERY_IMPORTANT_QUESTIONS")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        ((QuestionsDb) db).databaseCreated.postValue(true);
                    }
                })
                .build();
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return databaseCreated;
    }
}
