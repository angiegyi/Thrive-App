package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity
public class checkIn {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "checkInId")
    private int checkInId;

    @ColumnInfo(name = "")
    private String mood;

    @ColumnInfo(name = "")
    private Date date;

    @ColumnInfo(name = "")
    private Time time;

    @ColumnInfo(name = "")
    private String reason;

    @ForeignKey(entity = activity.class, parentColumns = "activityName", childColumns = "activityName")
    @ColumnInfo(name = "activityName")
    private String activityName;

    @ColumnInfo(name = "activityCompleted")
    private boolean activityCompleted;

    @ColumnInfo(name = "activityRating")
    private int activityRating;

}
