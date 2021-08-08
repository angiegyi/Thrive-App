package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mood")
public class Mood {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "mood_name")
    private String mood_name;

    @ColumnInfo(name = "mood_isPositive")
    private boolean mood_isPositive;

    /*
    Constructor
     */

    public Mood(@NonNull String mood_name, boolean mood_isPositive) {
        this.mood_name = mood_name;
        this.mood_isPositive = mood_isPositive;
    }

/*
    Methods
     */

    @NonNull
    public String getMood_name() {
        return mood_name;
    }

    public void setMood_name(@NonNull String mood_name) {
        this.mood_name = mood_name;
    }

    public boolean isMood_isPositive() {
        return mood_isPositive;
    }

    public void setMood_isPositive(boolean mood_isPositive) {
        this.mood_isPositive = mood_isPositive;
    }
}
