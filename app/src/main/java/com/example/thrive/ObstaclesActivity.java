package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Obstacle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ObstaclesActivity extends AppCompatActivity {

    ThriveViewModel mThriveViewModel;
    ArrayAdapter adapter;
    FloatingActionButton fab;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obstacles);
        initFab();
        initData();
    }

    private void initData(){
        list = findViewById(R.id.obstacleList);

        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        // gets all the data -> need to add as list items
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
        fab.setOnClickListener(view -> startActivity(new Intent(ObstaclesActivity.this, NewObstacleActivity.class)));
    }
}

