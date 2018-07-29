package com.tkachuk.dday.ui.rating;

import android.app.Application;

import com.tkachuk.dday.data.db.RatingViewModel;
import com.tkachuk.dday.util.DateManager;
import com.tkachuk.dday.data.model.Rating;

import java.util.Date;

public class RatingPresenter implements RatingContract.RatingPresenter{

    private RatingViewModel ratingViewModel;

    RatingPresenter(Application application) {
        ratingViewModel = new RatingViewModel(application);
    }

    @Override
    public void addRating(Date date, Float rating) {

        if(date==null){
            date = DateManager.getCurrentDate();
        }

        date = DateManager.dateWithoutTime(date);

        ratingViewModel.insert(new Rating(date, rating));
    }
}
