package com.example.thrive.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class category {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="categoryName")
    private String name;

    public category(@NonNull String name) {
        this.name = name;
    }
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
