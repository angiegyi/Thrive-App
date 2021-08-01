package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity(tableName = "checkIn")
public class checkIn {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "checkInId")
    private int checkInId;

    @ColumnInfo(name = "mood")
    private String mood;

    @ColumnInfo(name = "date")
    private int date;

    @ColumnInfo(name = "time")
    private int time;

    @ColumnInfo(name = "reason")
    private String reason;

    @ForeignKey(entity = activity.class, parentColumns = "activityName", childColumns = "activityName")
    @ColumnInfo(name = "activityName")
    private String activityName;

    @ColumnInfo(name = "activityCompleted")
    private boolean activityCompleted;

    @ColumnInfo(name = "activityRating")
    private int activityRating;

    public int getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(int checkInId) {
        this.checkInId = checkInId;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public boolean isActivityCompleted() {
        return activityCompleted;
    }

    public void setActivityCompleted(boolean activityCompleted) {
        this.activityCompleted = activityCompleted;
    }

    public int getActivityRating() {
        return activityRating;
    }

    public void setActivityRating(int activityRating) {
        this.activityRating = activityRating;
    }

}
