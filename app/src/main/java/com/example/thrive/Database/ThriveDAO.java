package com.example.thrive.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;

import java.util.List;

@Dao
public interface ThriveDAO {
    @Query("select * from Value")
    LiveData<List<Value>> getAllValues();

    @Query("SELECT COUNT(value_name) FROM Value")
    LiveData<Integer> getRowCount();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addValue(Value value);

    @Query("delete from Value where value_name=:valueName")
    void deleteValue(String valueName);

    @Query("delete FROM Value")
    void deleteAllValues();

    @Query(value = "insert into CATEGORY (category_name) VALUES (:category_name)")
    void addCategory(String category_name);

    @Query("select * from category")
    LiveData<List<Category>> getAllCategories();

//    @Query(value = "insert into MOOD (mood_name, mood_isPositive) VALUES (:mood_name, :isPositive)")
//    void addMood(String mood_name, boolean isPositive);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMood(Mood mood);

    @Query("select * from mood")
    LiveData<List<Mood>> getAllMoods();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addObstacle(Obstacle obstacle);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addObstacle_value(Obstacle_value obstacle_value);

    @Query("SELECT * from OBSTACLE_VALUE")
    LiveData<List<Obstacle_value>> getAllObstacle_values();

    @Query("SELECT * FROM OBSTACLE")
    LiveData<List<Obstacle>> getAllObstacles();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addHabit(Habit habit);

}
