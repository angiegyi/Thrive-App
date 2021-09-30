package com.example.thrive.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tool")
public class Tool {

    @PrimaryKey
    @ColumnInfo(name = "tool_name")
    @NonNull
    private String toolName;


    @ColumnInfo(name = "icon_name")
    private String icon_name;

    public Tool(String toolName) {
        this.toolName = toolName;
    }

    @NonNull
    public String getToolName() {
        return toolName;
    }

    public void setToolName(@NonNull String toolName) {
        this.toolName = toolName;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }
}
