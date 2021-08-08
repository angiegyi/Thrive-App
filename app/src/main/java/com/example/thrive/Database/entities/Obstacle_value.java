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

    public Obstacle_value(@NonNull String obstacleName, @NonNull String valueName) {
        this.obstacleName = obstacleName;
        this.valueName = valueName;
    }

    @NonNull
    public String getObstacleName() {
        return obstacleName;
    }

    public void setObstacleName(@NonNull String obstacleName) {
        this.obstacleName = obstacleName;
    }

    @NonNull
    public String getValueName() {
        return valueName;
    }

    public void setValueName(@NonNull String valueName) {
        this.valueName = valueName;
    }
}
