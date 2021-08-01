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
    @NonNull
    private String obstacleName;

    @ForeignKey(entity = category.class, parentColumns = "categoryName", childColumns = "category")
    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "obstacleDescription")
    private String description;

    @NonNull
    @ColumnInfo(name = "notifyFrequency", defaultValue = "0")
    private int notifyFrequency;

    public String getObstacleName() {
        return obstacleName;
    }

    public void setObstacleName(String obstacleName) {
        this.obstacleName = obstacleName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotifyFrequency() {
        return notifyFrequency;
    }

    public void setNotifyFrequency(int notifyFrequency) {
        this.notifyFrequency = notifyFrequency;
    }
}
