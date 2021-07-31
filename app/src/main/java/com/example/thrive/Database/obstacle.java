package com.example.thrive.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class obstacle {

    @PrimaryKey
    @ColumnInfo(name="obstacleName")
    private String obstacleName;

    @ForeignKey(entity = category.class, parentColumns = "categoryName", childColumns = "category")
    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "obstacleDescription")
    private String description;

    @NonNull
    @ColumnInfo(name = "notifyFrequency", defaultValue = "0")
    private int notifyFrequency;


}
