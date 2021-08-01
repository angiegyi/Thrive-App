package com.example.thrive.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ThriveDAO {
    @Query("select * from value")
    LiveData<List<value>> getAllValues();

    @Query("SELECT COUNT(valueName) FROM value")
    LiveData<Integer> getRowCount();

    @Insert
    void addValue(value value);

    @Query("delete from value where valueName=:valueName")
    void deleteValue(String valueName);

    @Query("delete FROM value")
    void deleteAllValues();
}
