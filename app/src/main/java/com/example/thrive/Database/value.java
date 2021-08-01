package com.example.thrive.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "value")

public class value {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name="valueName")
    private String name;

    @ForeignKey(entity = category.class, parentColumns ="categoryName", childColumns ="category", onDelete = RESTRICT, onUpdate = CASCADE)
    @ColumnInfo(name="category")
    private String category;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
