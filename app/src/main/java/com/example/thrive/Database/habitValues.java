package com.example.thrive.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class habitValues {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "habitName")
    @ForeignKey(entity = habit.class, parentColumns = "nameName", childColumns = "habitName" )
    private String habitName;

    @ForeignKey(entity = value.class, parentColumns ="valueName", childColumns = "valueName")
    @ColumnInfo(name="valueName")
    private String valueName;

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
