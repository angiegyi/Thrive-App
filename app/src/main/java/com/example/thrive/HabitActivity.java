package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HabitActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView habitRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    HabitRecyclerAdapter habitAdapter;
    ThriveViewModel mThriveViewModel;
    ArrayList<JSONObject> habitData = new ArrayList<>();

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
    private void initData(){
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        mThriveViewModel.getAllHabits().observe(this, newData -> {
            for (Object obj : newData) {
                if (obj != null){
                    habitData.add(objectToJSONObject(obj));
                }
            }
            habitAdapter = new HabitRecyclerAdapter(habitData);
            habitRecyclerView.setAdapter(habitAdapter);
            // habitAdapter.notifyDataSetChanged();
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


    private void onClickFab(){
        fab = findViewById(R.id.fab_new_habit);
        fab.setOnClickListener(view -> startActivity(new Intent(HabitActivity.this, NewHabitActivity.class)));
    }
}
