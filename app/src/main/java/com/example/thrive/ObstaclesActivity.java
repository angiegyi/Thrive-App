package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ObstaclesActivity extends AppCompatActivity {

    ThriveViewModel mThriveViewModel;
    ArrayAdapter adapter;
    FloatingActionButton fab;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obstacles);String[] categories = {"people", "education", "mindfulness", "self-help",
                "physical health", "mental health", "financial", "relationships", "family",
                "recreational", "career", "creativity", "networking", "pets", "other"};
        initFab();
        initData();

    }

    private void initData(){
        list = findViewById(R.id.obstacleList);
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        // gets all the data -> need to add as list items

        try {
            Obstacle obs1 = new Obstacle("covid", "it suggs", false, 13, 0, 1234560);
            mThriveViewModel.insert(obs1);
            //inserting test value into database
            Value testValue = new Value("fit3162", "education");
            mThriveViewModel.insert(testValue);
            //inserting a related value and obstacle to the Obstacle_value table
            Obstacle_value obstacle_value_test = new Obstacle_value(obs1.getObstacleName(), testValue.getName());
            mThriveViewModel.insert(obstacle_value_test);
        } catch(Exception e) {
            //Log.i("TESTING", "initData: entities already added into DB");
        }
        mThriveViewModel.getAllValues().observe(this, newData -> {
            // add all array to listItems
            adapter=new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, newData.toArray());
            System.out.println("called from init data");
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });
    }



    private void initFab(){
        fab = findViewById(R.id.fab);
        // If fab button is clicked, the add obstacle activity is shown
        fab.setOnClickListener(view -> startActivity(new Intent(ObstaclesActivity.this, valuesActivity.class)));
    }
}

