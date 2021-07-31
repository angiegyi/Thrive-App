package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activity")
public class activity {

    @PrimaryKey
    @ColumnInfo(name = "activityName")
    private String activityName;

    @ColumnInfo(name = "activityDescription")
    private String activityDescription;

    @ColumnInfo(name = "activityRating")
    private String activityRating;

    @ColumnInfo(name = "notifyFrequency", defaultValue = "0")
    private int frequency;


}
