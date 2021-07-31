package com.example.thrive.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class habitValues {


    @PrimaryKey
    @ColumnInfo(name = "habitName")
    @ForeignKey(entity = habit.class, parentColumns = "nameName", childColumns = "habitName" )
    private String habitName;

    @PrimaryKey
    @ForeignKey(entity = value.class, parentColumns ="valueName", childColumns = "valueName")
    @ColumnInfo(name="valueName")
    private String valueName;

}
