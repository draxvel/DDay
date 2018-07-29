package com.tkachuk.dday.ui;

import com.tkachuk.dday.data.model.Rating;

import java.util.List;

public class MainPresenter implements MainContract.MainPresenter{

    @Override
    public Float getAverageValue(List<Rating> ratingList) {
        Float sum = 0.0f;
        Integer count = ratingList.size();

        for(Rating r: ratingList){
            sum+=r.getMark();
        }
        return convertToPErCent(sum/count);
    }

    public Float convertToPErCent(Float value){
        return value*2*10;
    }
}
