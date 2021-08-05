package com.example.thrive.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ThriveRepository {
    // a java class that provides easy and clean api so that the application can access different
    // data sources
    // gateway to all data sources. one of them being the db

    private ThriveDAO mThriveDAO;
    private LiveData<List<value>> mAllValues;

    ThriveRepository(Application application) {
        ThriveDatabase db = ThriveDatabase.getDatabase(application);
        mThriveDAO = db.ThriveDAO();
        mAllValues = mThriveDAO.getAllValues();
    }

    LiveData<List<value>> getmAllValues() {
        return mAllValues;
    }

    void insert(value value) {
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


}