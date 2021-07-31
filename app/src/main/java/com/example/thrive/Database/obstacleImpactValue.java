package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "obstacleImpactValue")
public class obstacleImpactValue {

    @PrimaryKey
    @ForeignKey(entity = obstacle.class, parentColumns = "obstacleName", childColumns = "obstacleName")
    @ColumnInfo(name = "obstacleName")
    private String obstacleName;

    @PrimaryKey
    @ForeignKey(entity = value.class, parentColumns = "valueName", childColumns = "valueName")
    @ColumnInfo(name = "valueName")
    private String valueName;
}
