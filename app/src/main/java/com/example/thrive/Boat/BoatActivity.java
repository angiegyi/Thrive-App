package com.example.thrive.Boat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.RecentActivity;
import com.example.thrive.Database.entities.Tool;
import com.example.thrive.ProgressActivity;
import com.example.thrive.R;

import java.util.ArrayList;
import java.util.Objects;

public class BoatActivity extends AppCompatActivity {
    ThriveViewModel mThriveViewModel;
    BoatRecyclerAdapter boatAdapter;
    RecyclerView activityRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Tool> activityData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boat);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Tools");
        activityRecyclerView = findViewById(R.id.boat_reyclerView);
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
        boatAdapter = new BoatRecyclerAdapter(mThriveViewModel, getApplicationContext());
        mThriveViewModel.getTools().observe(this, newData -> {
            boatAdapter.resetData();
            for (Tool obj : newData) {
                if (obj != null){
//                    Log.i("test", "RecentActivity: " + obj.getActivityName());
                    activityData.add(obj);
                }
            }
            boatAdapter.setData(activityData);
            activityRecyclerView.setAdapter(boatAdapter);
        });
    }
}
