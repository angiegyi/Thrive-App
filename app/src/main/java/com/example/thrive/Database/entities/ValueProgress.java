package com.example.thrive.Database.entities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

@Entity(tableName = "value_progress", foreignKeys = {@ForeignKey(entity = Value.class, parentColumns = "value_name",
        childColumns = "progress_value")})
public class ValueProgress {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="progress_id")
    private int progressId;

    @NonNull
    @ColumnInfo(name="progress_value")
    private final String progressValue;

    @ColumnInfo(name="progress_day")
    private final int day;

    @ColumnInfo(name="progress_month")
    private final int month;

    @ColumnInfo(name="progress_year")
    private final int year;

    @ColumnInfo(name="progress_total")
    private int total = 0;

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public ValueProgress(@NonNull String progressValue, int day, int month, int year) {
        this.progressValue = progressValue;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public ValueProgress(@NonNull String progressValue, Date date) {
        this.progressValue = progressValue;
        String dateStr = dateFormat.format(date);
        StringTokenizer stringTokenizer = new StringTokenizer(dateStr, "/");
        this.day = Integer.parseInt(stringTokenizer.nextToken());
        this.month = Integer.parseInt(stringTokenizer.nextToken());
        this.year = Integer.parseInt(stringTokenizer.nextToken());
    }

    private void incrementTotalByOne(){
        this.total += 1;
    }

    @NonNull
    public String getProgressValue() {
        return progressValue;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getTotal() {
        return total;
    }

    public Date getDate(){
        try {
            return dateFormat.parse(day+"/"+month+"/"+year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDateStr(){
        return day+"/"+month+"/"+year;
    }
}
