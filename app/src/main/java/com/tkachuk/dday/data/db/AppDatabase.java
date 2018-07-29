package com.tkachuk.dday.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.tkachuk.dday.data.db.dao.RatingDao;
import com.tkachuk.dday.data.model.Rating;
import com.tkachuk.dday.util.DateConverter;

@Database(entities = {Rating.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract RatingDao getRatingDao();

    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(final Context context){
        if(appDatabase == null){
            synchronized (AppDatabase.class){
                if(appDatabase == null){
                    appDatabase = Room
                            .databaseBuilder(context, AppDatabase.class, "rating_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDatabase;
    }
}
