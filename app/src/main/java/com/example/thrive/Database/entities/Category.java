package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {

    /*
    Columns
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="category_name")
    private String name;

    /*
    Constructor
     */
    public Category(@NonNull String name) {
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
