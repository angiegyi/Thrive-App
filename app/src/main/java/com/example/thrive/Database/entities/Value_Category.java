package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "value_category", primaryKeys = {"value_name", "category_name"},
        foreignKeys = {@ForeignKey(entity = Value.class,
            parentColumns = "value_name",
            childColumns = "value_name",
            onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Category.class,
                        parentColumns = "category_name",
                        childColumns = "category_name",
                        onDelete = ForeignKey.CASCADE)
        } )
public class Value_Category {

    @ColumnInfo(name = "value_name")
    @NonNull
    String value_name;

    @ColumnInfo(name = "category_name")
    @NonNull
    String category_name;

    /*
    Constructor
     */
    public Value_Category(@NonNull String category_name) {
        this.category_name = category_name;
    }

    /*
    Methods
     */
    @NonNull
    public String getValue_name() {
        return value_name;
    }

    public void setValue_name(@NonNull String value_name) {
        this.value_name = value_name;
    }

    @NonNull
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(@NonNull String category_name) {
        this.category_name = category_name;
    }
}
