package com.example.thrive;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewHookActivity extends AppCompatActivity {

    // DB Model
    ThriveViewModel mThriveViewModel;

    // Hook Title
    TextInputLayout hookTitleTextLayout;
    TextInputEditText hookTitleEditText;
    String newTitle;

    // Hook Obstacle
    TextInputLayout hookTextInputLayout;
    AutoCompleteTextView hookInput;
    ArrayList<String> arrayList_obstacles;
    ArrayAdapter<String> arrayListAdapter_obstacles;
    String selectedObstacle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_hook);

        // Hook Title
        hookTitleTextLayout = findViewById(R.id.hookTitleEditLayout);
        hookTitleEditText = findViewById(R.id.hookTitleTextField);

        // Select Obstacles -> Values -> need to add values to database
        hookTextInputLayout = findViewById(R.id.hookTextDropdownLayout);
        hookInput = findViewById(R.id.hookInput);
        arrayList_obstacles = new ArrayList<>();
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        // Pull values from DB and add to drop-down
        mThriveViewModel.getAllValues().observe(this, newData -> {
            for (Object obj : newData) {
                try {
                    arrayList_obstacles.add(objectToJSONObject(obj).get("name").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        arrayListAdapter_obstacles = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_obstacles);
        hookInput.setAdapter(arrayListAdapter_obstacles);
        hookInput.setThreshold(1);
    }

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

    public void onClick(){
        newTitle = hookTitleEditText.getEditableText().toString();
        selectedObstacle = (hookTextInputLayout.getEditText()).getText().toString();

    }

    public void addHook(String obstacle){
//        try {
//            Hook obs1 = new Hook(newTitle, obstacle);
//            mThriveViewModel.insert(obs1);
//            //inserting a related value and obstacle to the Obstacle_value table
//            Obstacle_value obstacle_value = new Obstacle_value(newTitle, value);
//            mThriveViewModel.insert(obstacle_value);
//            Toast.makeText(getApplicationContext(),
//                    "New Hook: " + newTitle + " added." ,
//                    Toast.LENGTH_LONG).show();
//        } catch(Exception e) {
//            Log.i("RESPONSE", e.toString());
//        }
    }

}
