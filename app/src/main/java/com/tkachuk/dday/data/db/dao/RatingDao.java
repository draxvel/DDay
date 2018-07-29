package com.tkachuk.dday.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.tkachuk.dday.data.model.Rating;
import com.tkachuk.dday.util.DateConverter;

import java.util.Date;
import java.util.List;

@Dao
public interface RatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Rating rating);

    @Delete
    void delete(Rating rating);

    @Query("SELECT * FROM rating WHERE date = :currentDate")
    LiveData<Rating> getRatingForCurrentDay(Date currentDate);

    @Query("SELECT * FROM rating WHERE date >=:to AND date<=:from")
    LiveData<List<Rating>> getRatingOfPeriod(Date from, Date to);

    @Query("SELECT * FROM rating")
    List<Rating> getAll();

}
