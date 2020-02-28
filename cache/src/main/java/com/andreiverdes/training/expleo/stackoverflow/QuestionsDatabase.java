package com.andreiverdes.training.expleo.stackoverflow;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.andreiverdes.training.expleo.stackoverflow.dao.QuestionDao;
import com.andreiverdes.training.expleo.stackoverflow.model.DbQuestion;

@Database(entities = {
        DbQuestion.class
}, version = 1)
public abstract class QuestionsDatabase extends RoomDatabase {

    private static QuestionsDatabase instance;

    public abstract QuestionDao questionDao();

    private MutableLiveData<Boolean> databaseCreated = new MutableLiveData<>();

    public static QuestionsDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (QuestionsDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context);
                }
            }
        }
        return instance;
    }

    private static QuestionsDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, QuestionsDatabase.class, "VERY_IMPORTANT_QUESTIONS")
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        instance.databaseCreated.postValue(true);
                    }
                })
                .build();
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return databaseCreated;
    }
}
