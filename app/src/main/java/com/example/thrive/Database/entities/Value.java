package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "value", foreignKeys = {@ForeignKey(entity = Category.class, parentColumns = "category_name",
childColumns = "category_name")})

public class Value {

    /*
    Columns
     */
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name="value_name")
    private String name;


    @NonNull
    @ColumnInfo(name="category_name")
    private String category;

    /*
    Constructor
     */
    public Value(@NonNull String name, String category) {
        this.name = name;
        this.category = category;
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

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

}
