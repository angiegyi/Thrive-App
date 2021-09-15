package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.Obstacle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.ArrayList;

public class ObstaclesActivity extends AppCompatActivity {

    ThriveViewModel mThriveViewModel;
    FloatingActionButton fab;
    ArrayList<Obstacle> data = new ArrayList<>();
    RecyclerView obstacleRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ObstacleRecyclerAdapter obstacleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obstacles);
        initFab();
        obstacleRecyclerView = findViewById(R.id.obstacle_recycler);
        layoutManager = new LinearLayoutManager(this);
        obstacleRecyclerView.setLayoutManager(layoutManager);
        initData();
        setTitle("My Obstacles");
    }

    /**
     * Initialises all data for the application including addapter logic.
     */
    private void initData(){
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        obstacleAdapter = new ObstacleRecyclerAdapter(getApplicationContext());
        mThriveViewModel.getAllObstacles().observe(this, newData -> {
            obstacleAdapter.resetData();
            for (Obstacle obj : newData) {
                if (obj != null) {
                    data.add(obj);
                }
            }
            obstacleAdapter.setData(data);
            obstacleRecyclerView.setAdapter(obstacleAdapter);
        });
    }

    /**
     * Converts Java objects to JSON such that attributes can be accessed.
     * @param object the java object to be converted
     * @return new JSON object
     */
    public static JSONObject objectToJSONObject(Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONObject obj = null;

        try {
            obj = new JSONObject(ow.writeValueAsString(object));
        } catch (JsonProcessingException | JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Initialises the FAB button and sets on click listner
     */
    private void initFab(){
        fab = findViewById(R.id.fab);
        // If fab button is clicked, the add obstacle activity is shown
        fab.setOnClickListener(view -> startActivity(new Intent(ObstaclesActivity.this, NewObstacleActivity.class)));
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ObstaclesActivity.this, MainActivity.class));
        finish();
    }
}

