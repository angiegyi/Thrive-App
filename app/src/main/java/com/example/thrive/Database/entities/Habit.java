package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entity")
public class Habit {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "habit_name")
    private String name;

    @ColumnInfo(name = "habit_period")
    private String period;
    // determines if you should do this task weekly or daily or fortnightly

    @ColumnInfo(name = "habit_frequency")
    private int frequency;
    // determines how many times you want to complete the task per period

    @ColumnInfo(name = "habit_measurement")
    private int measurement;
    // how is the unit measure for the counter. e.g. hours, no of times

    @ColumnInfo(name = "habit_reminder_on")
    private boolean reminderOn;
    // to determine if they want notifications on

    @ColumnInfo(name = "reminder_hour")
    private int reminderHour;

    @ColumnInfo(name = "reminder_minutes")
    private int reminderMinutes;

    @ColumnInfo(name = "reminder_days")
    private int reminderDays;

    //toolLink, may quickly link an appropriate activity or tool to this habit


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
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

    public boolean isReminderOn() {
        return reminderOn;
    }

    public void setReminderOn(boolean reminderOn) {
        this.reminderOn = reminderOn;
    }

    public int getReminderHour() {
        return reminderHour;
    }

    public void setReminderHour(int reminderHour) {
        this.reminderHour = reminderHour;
    }

    public int getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(int reminderDays) {
        this.reminderDays = reminderDays;
    }

    public int getReminderMinutes() {
        return reminderMinutes;
    }

    public void setReminderMinutes(int reminderMinutes) {
        this.reminderMinutes = reminderMinutes;
    }
}
