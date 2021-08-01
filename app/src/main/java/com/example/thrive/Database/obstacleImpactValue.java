package com.example.thrive.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "obstacleImpactValue", primaryKeys = {"obstacleName", "valueName"})
public class obstacleImpactValue {

    @ForeignKey(entity = obstacle.class, parentColumns = "obstacleName", childColumns = "obstacleName")
    @ColumnInfo(name = "obstacleName")
    @NonNull
    private String obstacleName;

    @ForeignKey(entity = value.class, parentColumns = "valueName", childColumns = "valueName")
    @ColumnInfo(name = "valueName")
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
