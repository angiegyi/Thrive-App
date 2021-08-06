package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activity")
public class Activity {

    @PrimaryKey
    @ColumnInfo(name = "activity_name")
    @NonNull
    private String activityName;


    @ColumnInfo(name = "activityDescription")
    private String activityDescription;

    @ColumnInfo(name = "activityRating")
    private String activityRating;

    @ColumnInfo(name = "notifyFrequency", defaultValue = "0")
    private int frequency;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getActivityRating() {
        return activityRating;
    }

    public void setActivityRating(String activityRating) {
        this.activityRating = activityRating;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
