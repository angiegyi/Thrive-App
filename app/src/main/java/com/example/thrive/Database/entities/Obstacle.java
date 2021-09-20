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

    @ColumnInfo(name = "value_name")
    private String valueName;

    /*
    Constructor
     */

    public Obstacle(@NonNull String obstacleName, String description, int importance, String valueName) {
        this.obstacleName = obstacleName;
        this.description = description;
        this.importance = importance;
        this.valueName = valueName;
    }

    @NonNull
    public String getObstacleName() {
        return obstacleName;
    }

    public String getDescription() {
        return description;
    }

    public String getValueName() { return valueName; }

    public int getImportance() {
        return importance;
    }
}
