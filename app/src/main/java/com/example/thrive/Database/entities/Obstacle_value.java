package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "obstacle_value", primaryKeys = {"obstacle_name", "value_name"},
        foreignKeys = {@ForeignKey(entity = Obstacle.class, parentColumns = "obstacle_name", childColumns = "obstacle_name"),
        @ForeignKey(entity = Value.class, parentColumns = "value_name", childColumns = "value_name")})
public class Obstacle_value {

    @ColumnInfo(name = "obstacle_name")
    @NonNull
    private String obstacleName;

    @ColumnInfo(name = "value_name")
    @NonNull
    private String valueName;

    public String getObstacleName() {
        return obstacleName;
    }

    public void setObstacleName(String obstacleName) {
        this.obstacleName = obstacleName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
