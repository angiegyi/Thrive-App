package com.example.thrive.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ThriveViewModel extends AndroidViewModel {

    // does the preprocessing of the data
    // the place where we implement logic
    // the class provides access to the database operations defined in repository to the controller

    private ThriveRepository mRepository;
    private LiveData<List<value>> mAllValues;

    public ThriveViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<value>> getmAllValues() {
        return mAllValues;
    }
    public void insert(value value) {
        mRepository.insert(value);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void deleteValue(String value){mRepository.deleteValue(value);}
}
