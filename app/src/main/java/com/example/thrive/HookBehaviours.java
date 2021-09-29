package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HookBehaviours extends AppCompatActivity {

    ThriveViewModel mThriveViewModel;
    ArrayAdapter adapter;
    FloatingActionButton fab;
    ListView list;
    ArrayList<String> objects = new ArrayList<>();
    String obstacleName;
    TextView obstacle_related_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_hooks);
        setTitle("Hook Behaviours");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            obstacleName = extras.getString("name");
            System.out.println(obstacleName);
        }
        obstacle_related_text = findViewById(R.id.obstacle_related_text);
        obstacle_related_text.setText(obstacleName);
        initData();
        initFab();
    }

    private void initData(){
        list = findViewById(R.id.hooksList);
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        mThriveViewModel.getAllHooksByObstacle(obstacleName).observe(this, newData -> {
            for (Object obj : newData) {
                if (obj != null) {
                    if (objects.size() < 10) {
                        try {
                            objects.add(objectToJSONObject(obj).getString("hook_behaviour"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        fab = findViewById(R.id.hookNewFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = obstacleName;
                Intent intent = new Intent(HookBehaviours.this,NewHookActivity.class);
                intent.putExtra("name", ""+ name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
//        startActivity(new Intent(HookBehaviours.this, ObstaclesActivity.class));
//        finish();
        super.onBackPressed();
        super.onBackPressed();
    }
}
