package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ObstaclesViewOnboarding extends AppCompatActivity {

    Button addObstaclesButton;
    Button nextButton;
    ListView displayList;
    ThriveViewModel tvm;
    ArrayAdapter adapter;
    ArrayList<String> obstaclesListOut = new ArrayList<>();

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values xml
        setContentView(R.layout.onboarding_view_obstacles);

        // Init add values button
        initAddObstacles();

        // Init next button
        initNext();

        // Init all obstacles
        initList();

    }

    private void initList(){
        displayList = findViewById(R.id.obstaclesOnboardList);

        tvm = new ViewModelProvider(this).get(ThriveViewModel.class);
        tvm.getAllObstacles().observe(this, newData -> {

            for (Object obj : newData) {
                if (obj != null){
                    try {
                        obstaclesListOut.add(objectToJSONObject(obj).getString("obstacleName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        adapter = new ArrayAdapter(this, R.layout.list_item, obstaclesListOut.toArray());

        displayList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        });
    }

    public static JSONObject objectToJSONObject(Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONObject obj = null;

        try {
            obj = new JSONObject(ow.writeValueAsString(object));
        }
        catch (JsonProcessingException | JSONException e){
            e.printStackTrace();
        }

        return obj;
    }

    // Initialising next button to values insert page
    private void initAddObstacles(){
        addObstaclesButton = findViewById(R.id.add_obstacles_button);
        // If button is clicked
        addObstaclesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the values insert page
                Intent intent = new Intent(ObstaclesViewOnboarding.this, ObstaclesInsertOnboarding.class);
                startActivity(intent);
            }
        });
    }

    // Initialising next button to the next page
    private void initNext(){
        nextButton = findViewById(R.id.next_button_oi);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the next page
                Intent intent = new Intent(ObstaclesViewOnboarding.this, HooksStoryOnboarding.class);
                startActivity(intent);
            }
        });
    }

}
