package com.tkachuk.dday.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.tkachuk.dday.data.db.AppDatabase;
import com.tkachuk.dday.data.db.dao.RatingDao;
import com.tkachuk.dday.data.model.Rating;

import java.util.Date;
import java.util.List;

public class RatingRepository {
    private RatingDao ratingDao;

    public RatingRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application);
        ratingDao = appDatabase.getRatingDao();
    }

    public void insert(Rating rating){
        new insertAsyncTask(ratingDao).execute(rating);
    }

    public LiveData<Rating> getRatingForCurrentDay(Date currentDate){
        return ratingDao.getRatingForCurrentDay(currentDate);
    }

    public LiveData<List<Rating>> getRatingOfPeriod(Date from, Date to){
        return ratingDao.getRatingOfPeriod(from, to);
    }

    public List<Rating> getAll(){
        return ratingDao.getAll();
    }

    private static class insertAsyncTask extends AsyncTask<Rating, Void, Void>{

        private RatingDao ratingDao;

        insertAsyncTask(RatingDao dao){
            ratingDao = dao;
        }

        @Override
        protected Void doInBackground(Rating... ratings) {
            ratingDao.insert(ratings[0]);
            return null;
        }
    }
}
