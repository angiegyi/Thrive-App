package com.example.thrive.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "activityCategory", primaryKeys = {"activityName", "categoryName"})
public class activityCategory {

    @ForeignKey(entity = activity.class, parentColumns = "activityName", childColumns = "activityName")
    @ColumnInfo(name = "activityName")
    @NonNull
    private String activityName;

    @ForeignKey(entity = category.class, parentColumns = "categoryName", childColumns = "categoryName")
    @ColumnInfo(name = "categoryName")
    @NonNull
    private String categoryName;

    @NonNull
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(@NonNull String activityName) {
        this.activityName = activityName;
    }

    @NonNull
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@NonNull String categoryName) {
        this.categoryName = categoryName;
    }
}
