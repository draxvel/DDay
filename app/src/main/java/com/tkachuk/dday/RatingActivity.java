package com.tkachuk.dday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    private Button btn_submit;
    private RatingBar rb_general;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initView();
        initListener();
    }

    private void initView() {
        btn_submit = findViewById(R.id.btn_submit);
        rb_general = findViewById(R.id.rb_general);
    }

    private void initListener() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RatingActivity.this, String.valueOf(rb_general.getRating()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
