package com.example.thrive.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
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

    ThriveRepository(Application application) {
        ThriveDatabase db = ThriveDatabase.getDatabase(application);
        mThriveDAO = db.ThriveDAO();
        mAllValues = mThriveDAO.getAllValues();
        mAllCategories = mThriveDAO.getAllCategories();
        mAllMoods = mThriveDAO.getAllMoods();
    }

    LiveData<List<Value>> getAllValues() {
        return mAllValues;
    }

    void insert(Value value) {
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addValue(value));
    }

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

    void addCategory(String category_name){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addCategory(category_name));
    }

    LiveData<List<Category>> getAllCategories(){return mAllCategories;}

    void addMood(String mood_name, boolean isPositive){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addMood(mood_name, isPositive));
    }

    LiveData<List<Mood>> getAllMoods(){return mAllMoods;}

    void insert(Obstacle obstacle){
        ThriveDatabase.databaseWriteExecutor.execute(() -> mThriveDAO.addObstacle(obstacle));
    }
}