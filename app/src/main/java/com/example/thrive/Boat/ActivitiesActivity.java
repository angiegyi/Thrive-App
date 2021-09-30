package com.example.thrive.Boat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivitiesActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView activityRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ActivityRecyclerAdapter activityAdapter;
    ThriveViewModel mThriveViewModel;
    ArrayList<Activity> activityData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_activity);
        activityRecyclerView = findViewById(R.id.activity_recycler);
        layoutManager = new LinearLayoutManager(this);
        activityRecyclerView.setLayoutManager(layoutManager);
        initData();
    }

    /**
     * Initialises all data for the application including adapter logic.
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initData(){
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        activityAdapter = new ActivityRecyclerAdapter(mThriveViewModel, getApplicationContext());
        mThriveViewModel.getAllActivities().observe(this, newData -> {
            activityAdapter.resetData();
            for (Activity obj : newData) {
                if (obj != null){
                    activityData.add(obj);
                }
            }
            activityAdapter.setData(activityData);
            activityRecyclerView.setAdapter(activityAdapter);
        });
    }
}
