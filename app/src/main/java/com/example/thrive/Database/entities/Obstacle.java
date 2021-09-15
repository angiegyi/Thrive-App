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

    @ColumnInfo(name = "obstacle_importance")
    private int importance;

    /*
    Constructor
     */

    public Obstacle(@NonNull String obstacleName, String description, int importance) {
        this.obstacleName = obstacleName;
        this.description = description;
        this.importance = importance;
    }

    @NonNull
    public String getObstacleName() {
        return obstacleName;
    }

    public String getDescription() {
        return description;
    }

    public int getImportance() {
        return importance;
    }
}
