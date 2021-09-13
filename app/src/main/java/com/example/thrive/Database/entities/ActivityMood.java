package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "activityMood", primaryKeys = {"activity_name", "mood_name"}, foreignKeys = {
        @ForeignKey(entity= Activity.class, parentColumns = "activity_name", childColumns = "activity_name"),
        @ForeignKey(entity = Mood.class, parentColumns = "mood_name", childColumns = "mood_name")
})
public class ActivityMood {
    @NonNull
    @ColumnInfo(name = "activity_name")
    private String activityName;


    @NonNull
    @ColumnInfo(name = "mood_name")
    private String moodName;

    @ColumnInfo(name = "strength")
    private int strength;

    public ActivityMood(String activityName, String moodName, int strength) {
        this.activityName = activityName;
        this.moodName = moodName;
        this.strength = strength;
    }

    @NonNull
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(@NonNull String activityName) {
        this.activityName = activityName;
    }

    @NonNull
    public String getMoodName() {
        return moodName;
    }

    public void setMoodName(@NonNull String moodName) {
        this.moodName = moodName;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
