package com.tkachuk.dday.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tkachuk.dday.R;
import com.tkachuk.dday.data.db.RatingViewModel;
import com.tkachuk.dday.data.model.Rating;
import com.tkachuk.dday.ui.rating.RatingActivity;
import com.tkachuk.dday.util.DateManager;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private Button btn_rate;
    private TextView tv_day_pc;
    private TextView tv_30days_pc;

    private MainPresenter mainPresenter;
    private RatingViewModel ratingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingViewModel = new RatingViewModel(getApplication());

        initView();
        initPresenter();
        initListener();
    }

    private void initView() {
        btn_rate = findViewById(R.id.btn_rate);
        tv_day_pc = findViewById(R.id.tv_day_pc);
        tv_30days_pc = findViewById(R.id.tv_30days_pc);
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter();
    }

    private void initListener() {
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RatingActivity.class));
            }
        });

        ratingViewModel.getRatingOfPeriod(DateManager.getCurrentDate(), DateManager.getMinus30Date()).observe(
                this,
                new Observer<List<Rating>>() {
                    @Override
                    public void onChanged(@Nullable List<Rating> ratings) {

                        if(ratings!=null){
                            Log.d("draxvel: ratings: ", ratings.toString());

                            tv_30days_pc.setText(new DecimalFormat("#.##").format(mainPresenter.getAverageValue(ratings))+"%");
                        }
                    }
                });

        ratingViewModel.getRatingForCurrentDay(DateManager.getCurrentDate()).observe(this, new Observer<Rating>() {
            @Override
            public void onChanged(@Nullable Rating rating) {
                if(rating!=null){
                    tv_day_pc.setText(new DecimalFormat("#.##").format(mainPresenter.convertToPErCent(rating.getMark()))+"%");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
