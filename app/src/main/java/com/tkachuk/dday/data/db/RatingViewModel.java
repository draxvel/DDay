package com.tkachuk.dday.data.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.tkachuk.dday.data.RatingRepository;
import com.tkachuk.dday.data.model.Rating;

import java.util.Date;
import java.util.List;

public class RatingViewModel extends AndroidViewModel{

    private RatingRepository ratingRepository;

    public RatingViewModel(@NonNull Application application) {
        super(application);
        ratingRepository = new RatingRepository(application);
    }

    public LiveData<Rating> getRatingForCurrentDay(Date currentDate){
        return ratingRepository.getRatingForCurrentDay(currentDate);
    }

    public LiveData<List<Rating>> getRatingOfPeriod(Date from, Date to){
        return ratingRepository.getRatingOfPeriod(from, to);
    }

    public List<Rating> getAll(){
       return ratingRepository.getAll();
    }

    public void insert(Rating rating){
        ratingRepository.insert(rating);
    }
}
