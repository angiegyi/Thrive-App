package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "recentActivity", foreignKeys = {
        @ForeignKey(entity= Activity.class,
                parentColumns = "activity_name",
                childColumns = "activity_name")})
public class RecentActivity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "activity_name")
    private String activityName;


    /*
    The recent rank of an activity will determine the order. 0 will be the most recent activity
     */
    @ColumnInfo(name="recent_rank")
    private int recentRank;

    public RecentActivity(String activityName) {
        this.activityName = activityName;
    }

    @NonNull
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(@NonNull String activityName) {
        this.activityName = activityName;
    }

    public int getRecentRank() {
        return recentRank;
    }

    public void setRecentRank(int recentRank) {
        this.recentRank = recentRank;
    }

}
