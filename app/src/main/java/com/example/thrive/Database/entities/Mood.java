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

    @ColumnInfo(name = "mood_type")
    // 0, negative, 1 positive, 2, neutral
    private int mood_type;

    /*
    Constructor
     */

    public Mood(@NonNull String mood_name, int mood_type) {
        this.mood_name = mood_name;
        this.mood_type = mood_type;
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

    public int getMood_type() {
        return mood_type;
    }

    public void setMood_type(int mood_type) {
        this.mood_type = mood_type;
    }
}
