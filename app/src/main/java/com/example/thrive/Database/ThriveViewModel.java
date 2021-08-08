package com.example.thrive.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;

import java.util.List;

public class ThriveViewModel extends AndroidViewModel {

    // does the preprocessing of the data
    // the place where we implement logic
    // the class provides access to the database operations defined in repository to the controller

    private ThriveRepository mRepository;
    private LiveData<List<Value>> mAllValues;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<List<Mood>> mAllMoods;
    private LiveData<List<Obstacle_value>> mAllObstacle_values;

    public ThriveViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ThriveRepository(application);
        mAllValues = mRepository.getAllValues();
        mAllCategories = mRepository.getAllCategories();
        mAllMoods = mRepository.getAllMoods();
        mAllObstacle_values = mRepository.getAllObstacle_values();
    }

    public LiveData<List<Value>> getAllValues() {
        return mAllValues;
    }
    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }
    public void insert(Value value) {
        mRepository.insert(value);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void deleteValue(String value){mRepository.deleteValue(value);}
    public void addCategory(String category_name){mRepository.addCategory(category_name);}
    public void addMood(Mood mood){mRepository.addMood(mood);}
    public LiveData<List<Mood>> getAllMoods(){return mAllMoods;}
    public void insert(Obstacle obstacle){mRepository.insert(obstacle);}
    public void insert(Obstacle_value obstacle_value){mRepository.insert(obstacle_value);}
    public LiveData<List<Obstacle_value>> getAllObstacle_values(){return mAllObstacle_values;}

}
