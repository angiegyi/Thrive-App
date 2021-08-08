package com.example.thrive.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Value;

import java.util.List;

@Dao
public interface ThriveDAO {
    @Query("select * from Value")
    LiveData<List<Value>> getAllValues();

    @Query("SELECT COUNT(value_name) FROM Value")
    LiveData<Integer> getRowCount();

    @Insert
    void addValue(Value value);

    @Query("delete from Value where value_name=:valueName")
    void deleteValue(String valueName);

    @Query("delete FROM Value")
    void deleteAllValues();

    @Query(value = "insert into CATEGORY (category_name) VALUES (:category_name)")
    void addCategory(String category_name);

    @Query("select * from category")
    LiveData<List<Category>> getAllCategories();

    @Query(value = "insert into MOOD (mood_name, mood_isPositive) VALUES (:mood_name, :isPositive)")
    void addMood(String mood_name, boolean isPositive);

    @Query("select * from mood")
    LiveData<List<Mood>> getAllMoods();

    @Insert
    void addObstacle(Obstacle obstacle);

}
