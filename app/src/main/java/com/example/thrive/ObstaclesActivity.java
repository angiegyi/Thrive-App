package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
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
    ArrayAdapter adapter;
    FloatingActionButton fab;
    ListView list;
    ArrayList<String> objects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obstacles);
        initFab();
        initData();
    }

    /**
     * Initialises all data for the application including addapter logic.
     */
    private void initData(){
        list = findViewById(R.id.obstacleList);
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        mThriveViewModel.getAllObstacles().observe(this, newData -> {
            for (Object obj : newData) {
                if (obj != null){
                    try {
                        objects.add(objectToJSONObject(obj).getString("obstacleName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, objects.toArray());
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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
}

