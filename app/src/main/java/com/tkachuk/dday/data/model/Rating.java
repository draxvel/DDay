package com.tkachuk.dday.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(indices = {@Index(value = {"date"},
        unique = true)})
public class Rating {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private Date date;

    private Float mark;

    public Rating(Date date, Float mark) {
        this.date = date;
        this.mark = mark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "date=" + date +
                ", mark=" + mark +
                '}';
    }
}
