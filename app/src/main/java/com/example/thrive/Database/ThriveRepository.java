package com.example.thrive.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
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

import java.util.List;

public class ThriveRepository {
    // a java class that provides easy and clean api so that the application can access different
    // data sources
    // gateway to all data sources. one of them being the db

    private ThriveDAO mThriveDAO;
    private LiveData<List<Value>> mAllValues;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<List<Mood>> mAllMoods;
    private LiveData<List<Obstacle_value>> mAllObstacle_values;
    private LiveData<List<Obstacle>> mAllObstacles;
    private LiveData<List<Hook>> mAllHooks;
    private LiveData<List<Mood>> mAllPositiveMoods;
    private LiveData<List<Habit>> mAllHabits;
    private LiveData<List<HabitValue>> mAllHabitValues;


    ThriveRepository(Application application) {
        ThriveDatabase db = ThriveDatabase.getDatabase(application);
        mThriveDAO = db.ThriveDAO();
        mAllValues = mThriveDAO.getAllValues();
        mAllCategories = mThriveDAO.getAllCategories();
        mAllMoods = mThriveDAO.getAllMoods();
        mAllObstacle_values = mThriveDAO.getAllObstacle_values();
        mAllObstacles = mThriveDAO.getAllObstacles();
        mAllHooks = mThriveDAO.getAllHooks();
        mAllHabits = mThriveDAO.getAllHabits();
        mAllHabitValues = mThriveDAO.getAllHabitValues();
    }

    /*
    GET ALL FROM TABLE
     */
    LiveData<List<Value>> getAllValues() {return mAllValues;}
    LiveData<List<Category>> getAllCategories(){return mAllCategories;}
    LiveData<List<Mood>> getAllMoods(){return mAllMoods;}
    LiveData<List<Obstacle_value>> getAllObstacle_values(){ return mAllObstacle_values;}
    LiveData<List<Obstacle>> getAllObstacles(){return mAllObstacles;}
    LiveData<List<Hook>> getAllHooks(){return mAllHooks;}
    LiveData<List<Mood>> getAllPositiveOrNegativeMoods(int value){return mThriveDAO.getAllPositiveOrNegativeMoods(value);};
    LiveData<List<Habit>> getAllHabits(){return mAllHabits;}
    LiveData<List<HabitValue>> getAllHabitValues(){return mAllHabitValues;}
    List<Activity> findActivityByMoodName(String mood){return mThriveDAO.findActivityByMoodName(mood);}
    List<ActivityMood> getMoodAndActivity(String mood){return mThriveDAO.getMoodAndActivity(mood);}


    /*
    INSERT INTO DB
     */
    void insert(Value value) {
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addValue(value));
    }
    void addCategory(String category_name){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addCategory(category_name));
    }
    void addMood(Mood mood){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addMood(mood));
    }
    void insert(Habit habit) {
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addHabit(habit));
    }
    void insert(HabitValue habitValue) {
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addHabitValue(habitValue));
    }
    void insert(Obstacle obstacle){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addObstacle(obstacle));
    }
    void insert(Obstacle_value obstacle_value){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addObstacle_value(obstacle_value));
    }
    void insert(Hook hook){
        ThriveDatabase.databaseWriteExecutor.execute(()->mThriveDAO.addHook(hook));
    }

    void insert(CheckIn checkIn){
        ThriveDatabase.databaseWriteExecutor.execute(()->mThriveDAO.addCheckIn(checkIn));
    }
    /*
    DELETE METHODS
     */
    void deleteAll(){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.deleteAllValues();
        });
    }
    void deleteValue(String value){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.deleteValue(value);
        });
    }

    void deleteHook(String hook_behaviour){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.deleteHook(hook_behaviour);
        });
    }

    void deleteObstacle(String obstacle_name){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.deleteObstacle(obstacle_name);
        });
    }

    /*
    UPDATE METHODS
     */
    void updateHabitCounter(String habitName, int newCounter){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.updateHabitCounter(habitName, newCounter);
        });
    }
}