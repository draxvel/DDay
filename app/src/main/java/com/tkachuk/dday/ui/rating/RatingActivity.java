package com.tkachuk.dday.ui.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.tkachuk.dday.R;

import java.util.Date;

public class RatingActivity extends AppCompatActivity implements RatingContract.RatingView{

    private Button btn_submit;
    private RatingBar rb_general;

    private RatingPresenter ratingPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initView();
        initPresenter();
        initListener();
    }

    private void initView() {
        btn_submit = findViewById(R.id.btn_submit);
        rb_general = findViewById(R.id.rb_general);
    }

    private void initPresenter() {
        ratingPresenter = new RatingPresenter(getApplication());
    }

    private void initListener() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingPresenter.addRating((Date)getIntent().getSerializableExtra("date"),
                        rb_general.getRating());
                finish();
                }
        });
    }
}
