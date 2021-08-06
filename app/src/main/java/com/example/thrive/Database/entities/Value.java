package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "value")

public class Value {

    /*
    Columns
     */
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name="value_name")
    private String name;

    /*
    Constructor
     */
    public Value(@NonNull String name) {
        this.name = name;
    }

    /*
    Methods
    */
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }


}
