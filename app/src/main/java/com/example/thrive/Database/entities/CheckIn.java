package com.example.thrive.Database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "checkIn", foreignKeys = {@ForeignKey(entity = Mood.class, parentColumns = "mood_name", childColumns = "mood")})
public class CheckIn {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "checkId")
    private int checkId;

    @ColumnInfo(name = "mood")
    private String mood;

    @ColumnInfo(name = "reason")
    private String reason;

    @ColumnInfo(name = "check_date")
    private int date;

    @ColumnInfo(name = "check_month")
    private int month;

    @ColumnInfo(name = "check_year")
    private int year;

    @ColumnInfo(name = "check_hour")
    private int hour;

    @ColumnInfo(name = "check_minute")
    private int minute;


    //@ForeignKey(entity = activity.class, parentColumns = "activityName", childColumns = "activityName")
    @ColumnInfo(name = "activity_name")
    private String activityName;

    @ColumnInfo(name = "activity_completed")
    private boolean activityCompleted;

    @ColumnInfo(name = "activity_rating")
    private int activityRating;


    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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
