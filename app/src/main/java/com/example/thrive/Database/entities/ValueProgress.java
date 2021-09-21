package com.example.thrive.Database.entities;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

@Entity(tableName = "value_progress", foreignKeys = {@ForeignKey(entity = Value.class, parentColumns = "value_name",
        childColumns = "progress_value")}, indices = {@Index(value = {"progress_value", "progress_date"},
        unique = true)})
public class ValueProgress {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="progress_id")
    private int progressId;

    @NonNull
    @ColumnInfo(name="progress_value")
    private final String progressValue;

    @NonNull
    @ColumnInfo(name="progress_day")
    private final int day;

    @ColumnInfo(name="progress_month")
    private final int month;

    @ColumnInfo(name="progress_year")
    private final int year;

    @ColumnInfo(name="progress_date")
    private String date;

    @ColumnInfo(name="progress_total")
    private int total;

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public ValueProgress(@NonNull String progressValue, int day, int month, int year) {
        this.progressValue = progressValue;
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = day+"/"+month+"/"+year;
        this.total = 0;
    }

    public ValueProgress(@NonNull String progressValue, Date date) {
        this.progressValue = progressValue;
        String dateStr = dateFormat.format(date);
        Log.i("VALUEP", dateStr);
        StringTokenizer stringTokenizer = new StringTokenizer(dateStr, "/");
        this.day = Integer.parseInt(stringTokenizer.nextToken());
        this.month = Integer.parseInt(stringTokenizer.nextToken());
        this.year = Integer.parseInt(stringTokenizer.nextToken());
        this.date = dateStr;
        this.total = 0;
    }

    public void incrementTotalByOne(){
        this.total += 1;
    }

    public int getProgressId() {
        return progressId;
    }


    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
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

    public String getDate() {
        return date;
    }

    public Date getDateObj(){
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }


    public void setTotal(int total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
