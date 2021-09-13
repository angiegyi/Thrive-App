package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;

import java.util.ArrayList;


public class HabitsViewOnboarding extends AppCompatActivity {

    Button addHabitsButton;
    Button nextButton;
    ThriveViewModel tvm;
    RecyclerView habitRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    HabitRecyclerAdapterOnboarding habitAdapter;
    ArrayList<Habit> habitsListOut = new ArrayList<>();

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values xml
        setContentView(R.layout.onboarding_view_habits);

        // Init add values button
        initAddHabits();

        // Init next button
        initNext();

        habitRecyclerView = findViewById(R.id.habit_onboard_recycler);
        layoutManager = new LinearLayoutManager(this);
        habitRecyclerView.setLayoutManager(layoutManager);
        initList();

    }

    private void initList(){
        tvm = new ViewModelProvider(this).get(ThriveViewModel.class);
        habitAdapter = new HabitRecyclerAdapterOnboarding(tvm, getApplicationContext());
        tvm.getAllHabits().observe(this, newData -> {
            for (Habit obj : newData) {
                if (obj != null){
                    habitsListOut.add(obj);
                }
            }
            habitAdapter.setData(habitsListOut);
            habitRecyclerView.setAdapter(habitAdapter);
        });
    }

    // Initialising next button to values insert page
    private void initAddHabits(){
        addHabitsButton = findViewById(R.id.add_habits_button);
        // If button is clicked
        addHabitsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the values insert page
                Intent intent = new Intent(HabitsViewOnboarding.this, HabitsInsertOnboarding.class);
                startActivity(intent);
            }
        });
    }

    // Initialising next button to the next page
    private void initNext(){
        nextButton = findViewById(R.id.next_button_hi);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the next page
                Intent intent = new Intent(HabitsViewOnboarding.this, ObstaclesStoryOnboarding.class);
                startActivity(intent);
            }
        });
    }



}
