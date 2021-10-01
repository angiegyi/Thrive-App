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
import com.example.thrive.Database.entities.RecentActivity;
import com.example.thrive.Database.entities.Tool;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.ValueProgress;

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
    private LiveData<List<ValueProgress>> mAllValueProgesses;
    private LiveData<List<Activity>> mAllActivities;


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
        mAllValueProgesses = mThriveDAO.getAllValueProgresses();
        mAllActivities = mThriveDAO.getAllActivities();
    }

    /*
    GET ALL FROM TABLE
     */
    LiveData<List<Value>> getAllValues() {return mAllValues;}
    List<Value> getValues() {return mThriveDAO.getValues();}
    LiveData<List<Category>> getAllCategories(){return mAllCategories;}
    LiveData<List<Mood>> getAllMoods(){return mAllMoods;}
    LiveData<List<Obstacle_value>> getAllObstacle_values(){ return mAllObstacle_values;}
    LiveData<List<Obstacle>> getAllObstacles(){return mAllObstacles;}
    LiveData<List<Hook>> getAllHooks(){return mAllHooks;}
    LiveData<List<Hook>> getAllHooksByObstacle(String value){return mThriveDAO.getAllHooksByObstacle(value);};
    LiveData<List<Mood>> getMoodType(int value){return mThriveDAO.getMoodType(value);};
    LiveData<List<Habit>> getAllHabits(){return mAllHabits;}
    LiveData<List<HabitValue>> getAllHabitValues(){return mAllHabitValues;}
    LiveData<List<Activity>> getAllActivities(){return mAllActivities;}
    LiveData<List<RecentActivity>> getRecentActivities(){return mThriveDAO.getRecentActivities();}
    List<Activity> findActivityByMoodName(String mood){return mThriveDAO.findActivityByMoodName(mood);}
    List<ActivityMood> getMoodAndActivity(String mood){return mThriveDAO.getMoodAndActivity(mood);}
    LiveData<List<ValueProgress>> getAllValueProgesses(){return mAllValueProgesses;}
    List<ValueProgress> getValueProgesses(){return mThriveDAO.getValueProgresses();}
    List<ValueProgress> getAllValueProgessesDate(String date){return mThriveDAO.getValueProgressDate(date);}
    LiveData<List<Tool>> getTools(){return mThriveDAO.getTools();}

    /*
    FIND FROM TABLE
     */
    HabitValue getHabitValue(String habitName){return mThriveDAO.getHabitValue(habitName);}
    ValueProgress getValueProgress (String valueName, String date) {return mThriveDAO.getValueProgress(valueName, date);}
    List<RecentActivity> getLessRecentActivities(String activityName){ return mThriveDAO.getLessRecentActivities(activityName);}
    Activity getActivity(String activityName){return mThriveDAO.getActivity(activityName);}
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

    void insert(ValueProgress valueProgress){
        ThriveDatabase.databaseWriteExecutor.execute(()->mThriveDAO.addValueProgress(valueProgress));
    }

    void addRecentActivity(RecentActivity recentActivity){
        ThriveDatabase.databaseWriteExecutor.execute(()->mThriveDAO.addRecentActivity(recentActivity));
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

    void updateValueProgressTotal(String valueName, String valueDate, int newTotal){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.updateValueProgressTotal(valueName, valueDate, newTotal);
        });
    }

    void updateRecentRank(String activityName, int rank){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.updateRecentRank(activityName, rank);
        });
    }

    void updateActivityRating(String activityName, int value){
        ThriveDatabase.databaseWriteExecutor.execute(()->{
            mThriveDAO.updateActivityRating(activityName, value);
        });
    }

    /*
    OTHER METHODS
     */
    boolean containsActivity(String activityName){
        return mThriveDAO.containsActivity(activityName);
    }

    int recentActivityCount(){
        return mThriveDAO.recentActivityCount();
    }
}
