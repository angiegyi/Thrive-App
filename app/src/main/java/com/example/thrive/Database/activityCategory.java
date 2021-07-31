package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "activityCategory")
public class activityCategory {

    @ForeignKey(entity = activity.class, parentColumns = "activityName", childColumns = "activityName")
    @PrimaryKey
    @ColumnInfo(name = "activityName")
    private String activityName;

    @ForeignKey(entity = category.class, parentColumns = "categoryName", childColumns = "categoryName")
    @PrimaryKey
    @ColumnInfo(name = "categoryName")
    private String categoryName;
}
