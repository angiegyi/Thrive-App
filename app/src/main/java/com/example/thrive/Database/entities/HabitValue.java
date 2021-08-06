package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_value", foreignKeys = {@ForeignKey(entity = Habit.class, parentColumns = "habit_name", childColumns = "habit_name"),
        @ForeignKey(entity = Value.class, parentColumns = "value_name", childColumns = "value_name")})
public class HabitValue {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "habit_name")
    //@ForeignKey(entity = habit.class, parentColumns = "nameName", childColumns = "habitName" )
    private String habitName;

    //@ForeignKey(entity = value.class, parentColumns ="valueName", childColumns = "valueName")
    @ColumnInfo(name = "value_name")
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
