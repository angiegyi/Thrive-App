package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.thrive.Database.entities.Activity;

@Entity(tableName = "activity_category", primaryKeys = {"activity_name", "category_name"}, foreignKeys = {
        @ForeignKey(entity= Activity.class, parentColumns = "activity_name", childColumns = "activity_name")
})
public class Activity_category {

    //@ForeignKey(entity = activity.class, parentColumns = "activityName", childColumns = "activityName")
    @ColumnInfo(name = "activity_name")
    @NonNull
    private String activityName;

    //@ForeignKey(entity = category.class, parentColumns = "categoryName", childColumns = "categoryName")
    @ColumnInfo(name = "category_name")
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
