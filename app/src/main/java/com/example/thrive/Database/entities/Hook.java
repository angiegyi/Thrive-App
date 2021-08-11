package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "hook", foreignKeys = {@ForeignKey(entity = Obstacle.class, parentColumns = "obstacle_name",
childColumns = "obstacle_name")})
public class Hook {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "hook_behaviour")
    private String hook_behaviour;

    @NonNull
    @ColumnInfo(name = "obstacle_name")
    private String obstacle_name;

    public Hook(String hook_behaviour, String obstacle_name) {
        this.hook_behaviour = hook_behaviour;
        this.obstacle_name = obstacle_name;
    }

    public String getHook_behaviour() {
        return hook_behaviour;
    }

    public void setHook_behaviour(String hook_behaviour) {
        this.hook_behaviour = hook_behaviour;
    }

    public String getObstacle_name() {
        return obstacle_name;
    }

    public void setObstacle_name(String obstacle_name) {
        this.obstacle_name = obstacle_name;
    }
}
