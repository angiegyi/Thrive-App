package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "obstacle")
public class Obstacle {

    @PrimaryKey
    @ColumnInfo(name="obstacle_name")
    @NonNull
    private String obstacleName;

    @ColumnInfo(name = "obstacle_description")
    private String description;

    @NonNull
    @ColumnInfo(name = "reminder_on", defaultValue = "false")
    private boolean reminder_on;

    @ColumnInfo(name = "reminder_hour")
    private int reminderHour;

    @ColumnInfo(name = "reminder_minutes")
    private int reminderMinutes;

    @ColumnInfo(name = "reminder_days")
    private int reminderDays;

    /*
    Constructor
     */

    public Obstacle(@NonNull String obstacleName, String description, boolean reminder_on, int reminderHour, int reminderMinutes, int reminderDays) {
        this.obstacleName = obstacleName;
        this.description = description;
        this.reminder_on = reminder_on;
        this.reminderHour = reminderHour;
        this.reminderMinutes = reminderMinutes;
        this.reminderDays = reminderDays;
    }

    @NonNull
    public String getObstacleName() {
        return obstacleName;
    }

    public void setObstacleName(@NonNull String obstacleName) {
        this.obstacleName = obstacleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReminderHour() {
        return reminderHour;
    }

    public void setReminderHour(int reminderHour) {
        this.reminderHour = reminderHour;
    }

    public int getReminderMinutes() {
        return reminderMinutes;
    }

    public void setReminderMinutes(int reminderMinutes) {
        this.reminderMinutes = reminderMinutes;
    }

    public int getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(int reminderDays) {
        this.reminderDays = reminderDays;
    }

    public boolean isReminder_on() {
        return reminder_on;
    }

    public void setReminder_on(boolean reminder_on) {
        this.reminder_on = reminder_on;
    }
}
