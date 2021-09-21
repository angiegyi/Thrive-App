package com.example.thrive.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.ActivityMood;
import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.CheckIn;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.Hook;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.Hook;

import java.util.List;

@Dao
public interface ThriveDAO {

    /*
    SELECT ALL FROM TABLE
     */
    @Query("select * from Value")
    LiveData<List<Value>> getAllValues();

    @Query("SELECT COUNT(value_name) FROM Value")
    LiveData<Integer> getRowCount();

    @Query("select * from category")
    LiveData<List<Category>> getAllCategories();

    @Query("select * from mood")
    LiveData<List<Mood>> getAllMoods();

    @Query("SELECT * from OBSTACLE_VALUE")
    LiveData<List<Obstacle_value>> getAllObstacle_values();

    @Query("SELECT * FROM OBSTACLE ORDER BY obstacle_importance")
    LiveData<List<Obstacle>> getAllObstacles();

    @Query("SELECT * FROM HOOK")
    LiveData<List<Hook>> getAllHooks();

    @Query("SELECT * FROM habit")
    LiveData<List<Habit>> getAllHabits();

    @Query("SELECT * FROM HABIT_VALUE")
    LiveData<List<HabitValue>> getAllHabitValues();

    @Query("SELECT * FROM HOOK WHERE obstacle_name = :value")
    LiveData<List<Hook>> getAllHooksByObstacle(String value);

    @Query("SELECT * FROM MOOD WHERE mood_isPositive = :value")
    LiveData<List<Mood>> getAllPositiveOrNegativeMoods(int value);

    @Query("SELECT * FROM ACTIVITYMOOD WHERE mood_name =:mood ORDER BY mood_name")
    ActivityMood[] getActivityAndMood(String mood);

    @Query("SELECT * FROM ACTIVITY " +
            "INNER JOIN activityMood ON activitymood.activity_name = activity.activity_name " +
            "INNER JOIN mood ON mood.mood_name = activityMood.mood_name " +
            "WHERE (mood.mood_name LIKE :mood AND activityMood.strength >= 4) ORDER BY activity.activity_name")
    public List<Activity> findActivityByMoodName(String mood);

    @Query("SELECT * FROM ACTIVITYMOOD " +
            "INNER JOIN activity ON activity.activity_name = activityMood.activity_name " +
            "INNER JOIN mood ON mood.mood_name = activityMood.mood_name " +
            "WHERE (mood.mood_name LIKE :mood AND activityMood.strength >= 4) ORDER BY activity.activity_name")
    public List<ActivityMood> getMoodAndActivity(String mood);

    /*
    SELECT FIND
     */
    @Query("SELECT * FROM HABIT_VALUE WHERE habit_name = :habitName")
    HabitValue getHabitValue(String habitName);

    /*
    INSERT INTO DB
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addValue(Value value);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addObstacle(Obstacle obstacle);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addObstacle_value(Obstacle_value obstacle_value);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMood(Mood mood);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addHabit(Habit habit);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addHabitValue(HabitValue habitValue);

    @Query(value = "insert into CATEGORY (category_name) VALUES (:category_name)")
    void addCategory(String category_name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addHook(Hook hook);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCheckIn(CheckIn checkIn);


    /*
    DELETE QUERIES
     */

    @Query("delete from Value where value_name=:valueName")
    void deleteValue(String valueName);

    @Query("delete FROM Value")
    void deleteAllValues();

    @Query("delete FROM Hook where hook_behaviour=:hook_behaviour")
    void deleteHook(String hook_behaviour);

    @Query("delete FROM obstacle where obstacle_name=:obstacle_name")
    void deleteObstacle(String obstacle_name);

    /*
    UPDATE QUERIES
     */

    @Query("update habit set habit_counter=:newCounter where habit_name=:habitName")
    void updateHabitCounter(String habitName, int newCounter );


}
