package com.tkachuk.dday.ui.rating;

import java.util.Date;

public interface RatingContract {

    interface RatingView{

    }

    interface RatingPresenter{
        void addRating(final Date date, final Float rating);
    }
}
