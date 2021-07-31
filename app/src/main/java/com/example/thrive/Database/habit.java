package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "entity")
public class habit {

    @PrimaryKey
    @ColumnInfo(name = "habitName")
    private String name;

    @ColumnInfo(name = "period")
    private String period;
    // determines if you should do this task weekly or daily or fortnightly

    @ColumnInfo(name = "frequency")
    private int frequency;
    // determines how many times you want to complete the task per period

    @ColumnInfo(name = "measurement")
    private int measurement;
    // how is the unit measure for the counter. e.g. hours, no of times

    @ColumnInfo(name = "notify")
    private boolean notify;
    // to determine if they want notifications on

    @ColumnInfo(name = "notifyTime")
    private int notifyTime;

    @ColumnInfo(name = "notifyOnDays")
    private ArrayList<String> notifyOnDays;

    //toolLink, may quickly link an appropriate activity or tool to this habit


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getMeasurement() {
        return measurement;
    }

    public void setMeasurement(int measurement) {
        this.measurement = measurement;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public int getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(int notifyTime) {
        this.notifyTime = notifyTime;
    }

    public ArrayList<String> getNotifyOnDays() {
        return notifyOnDays;
    }

    public void setNotifyOnDays(ArrayList<String> notifyOnDays) {
        this.notifyOnDays = notifyOnDays;
    }
}
