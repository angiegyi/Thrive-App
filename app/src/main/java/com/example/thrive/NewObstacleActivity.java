package com.example.thrive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

public class NewObstacleActivity extends AppCompatActivity {

    // DB Model
    ThriveViewModel mThriveViewModel;

    // Obstacle Title
    TextInputLayout obstacleTitleTextLayout;
    TextInputEditText obstacleTitleEditText;
    String newTitle;

    // Obstacle Description
    TextInputLayout obstacleDescTextLayout;
    TextInputEditText obstacleDescEditText;
    String newDescription;

    // Value
    TextInputLayout valuesTextInputLayout;
    AutoCompleteTextView valuesInput;
    ArrayList<String> arrayList_values;
    ArrayAdapter<String> arrayListAdapter_values;
    String selectedValue;

    // Button
    Button createObstacleButton;

    // Importance
    Slider slider;
    Integer importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_obstacle);
        setTitle("New Obstacle");

        // Slider
        slider = findViewById(R.id.slider);
        //slider.addOnChangeListener((slider, value, fromUser) -> importance = (int) value);

        // Button
        createObstacleButton = findViewById(R.id.createObstacleButton);
        createObstacleButton.setOnClickListener(v -> onCLickHandler());

        // Obstacle Title
        obstacleTitleTextLayout = findViewById(R.id.obstacleNameTextField);
        obstacleTitleEditText = findViewById(R.id.obstacleNameEditLayout);

        // Obstacle Description
        obstacleDescTextLayout = findViewById(R.id.obstacleDescTextField);
        obstacleDescEditText = findViewById(R.id.hookTitleEditLayout);

        // Values -> need to add values to database
        valuesTextInputLayout = findViewById(R.id.moodsTextInputLayout);
        valuesInput = findViewById(R.id.relatedMoodsInput);
        arrayList_values = new ArrayList<>();
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        // Pull values from DB and add to drop-down
        mThriveViewModel.getAllValues().observe(this, newData -> {
            for (Object obj : newData) {
                try {
                    arrayList_values.add(objectToJSONObject(obj).get("name").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        arrayListAdapter_values = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_values);
        valuesInput.setAdapter(arrayListAdapter_values);
        valuesInput.setThreshold(1);
    }

    /**
     * Handler for the add obstacle value
     */
    @SuppressLint("NewApi")
    public void onCLickHandler(){
        if (TextUtils.isEmpty(obstacleTitleEditText.getText()) || TextUtils.isEmpty(obstacleDescEditText.getText()) || TextUtils.isEmpty(valuesTextInputLayout.getEditText().getText())) {
            // Error checking for title
            if (TextUtils.isEmpty(obstacleTitleEditText.getText())) {
                obstacleTitleEditText.setError("Fill in obstacle title");
            }
            // Error Checking Description
            if (TextUtils.isEmpty(obstacleDescEditText.getText())) {
                obstacleDescEditText.setError("Fill in obstacle description");
            }
            // Error Checking Value
            if (TextUtils.isEmpty(valuesTextInputLayout.getEditText().getText())) {
                valuesTextInputLayout.setError("Related value is required!");
            }
        }
        else {
            importance = (int) slider.getValue();
            newTitle = obstacleTitleEditText.getEditableText().toString();
            newDescription = obstacleDescEditText.getEditableText().toString();
            selectedValue = (valuesTextInputLayout.getEditText()).getText().toString();
            addObstacle(selectedValue);
            //startActivity(new Intent(NewObstacleActivity.this, ObstaclesActivity.class));
            super.onBackPressed();
        }
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

    /**
     * This method handles adding a new obstacle in the database.
     * @param value name of the related value from dropdow.
     */
    public void addObstacle(String value){
        try {
            Obstacle obs1 = new Obstacle(newTitle, newDescription, importance, value);
            mThriveViewModel.insert(obs1);
            //inserting a related value and obstacle to the Obstacle_value table
            Obstacle_value obstacle_value = new Obstacle_value(newTitle, value);
            mThriveViewModel.insert(obstacle_value);
            Toast.makeText(getApplicationContext(),
                    "New Value: " + newTitle + " added." ,
                    Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.i("O RESPONSE", e.toString());
        }
    }

}
