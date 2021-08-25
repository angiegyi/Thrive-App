package com.example.thrive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HabitActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView habitRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    HabitRecyclerAdapter habitAdapter;
    ThriveViewModel mThriveViewModel;
    ArrayList<Habit> habitData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habits);
        onClickFab();
        habitRecyclerView = findViewById(R.id.habit_recycler);
        layoutManager = new LinearLayoutManager(this);
        habitRecyclerView.setLayoutManager(layoutManager);
        initData();
    }

    /**
     * Initialises all data for the application including adapter logic.
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initData(){
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        habitAdapter = new HabitRecyclerAdapter(mThriveViewModel, getApplicationContext());
        mThriveViewModel.getAllHabits().observe(this, newData -> {
            for (Habit obj : newData) {
                if (obj != null){
                    habitData.add(obj);
                }
            }
            habitAdapter.setData(habitData);
            habitRecyclerView.setAdapter(habitAdapter);
            // habitAdapter.notifyDataSetChanged();
        });
    }


    private void onClickFab(){
        fab = findViewById(R.id.fab_new_habit);
        fab.setOnClickListener(view -> startActivity(new Intent(HabitActivity.this, NewHabitActivity.class)));
    }
}
