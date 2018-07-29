package com.tkachuk.dday.ui;

import com.tkachuk.dday.data.model.Rating;

import java.util.List;

public interface MainContract {
    interface MainView{

    }

    interface MainPresenter{
        Float getAverageValue(List<Rating> ratingList);
    }
}
