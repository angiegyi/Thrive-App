package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    // Time
    TextInputLayout notificationTimeInputLayout;
    AutoCompleteTextView notificationTimeInput;
    ArrayList<String> arrayList_notifications_time;
    ArrayAdapter<String> arrayListAdapter_notifications_time;
    Integer notification_time_value;
    List<String> times = Arrays.asList("12:00 AM", "1:00 AM","2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM"
    , "1:00 PM","2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM");
    Map<String, Integer> timeHours = new HashMap<>();

    // Days
    Map<CheckBox, String> checkBoxObjects = new HashMap<>();
    String days_data = "";

    // Button
    Button createObstacleButton;

    // Importance
    Slider slider;
    Integer importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_obstacle);

        // Init time hashmap
        for (int i = 0; i < 24; i++ ){
            timeHours.put(times.get(i), i);
        }

        // Slider
        slider = findViewById(R.id.slider);
        slider.addOnChangeListener((slider, value, fromUser) -> importance = Float.floatToIntBits(value));

        // Check Boxes
        setUpCheckBoxes();

        // Button
        createObstacleButton = findViewById(R.id.createObstacleButton);
        createObstacleButton.setOnClickListener(v -> onCLickHandler());

        // Obstacle Title
        obstacleTitleTextLayout = findViewById(R.id.obstacleNameTextField);
        obstacleTitleEditText = findViewById(R.id.obstacleNameEditLayout);

        // Obstacle Description
        obstacleDescTextLayout = findViewById(R.id.obstacleDescTextField);
        obstacleDescEditText = findViewById(R.id.obstacleDescEditLayout);

        // Values -> need to add values to database
        valuesTextInputLayout = findViewById(R.id.valuesTextInputLayout);
        valuesInput = findViewById(R.id.valuesInput);
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

        // Notifications
        notificationTimeInputLayout = findViewById(R.id.notifTimeInput);
        notificationTimeInput = findViewById(R.id.notificationTimeInput);
        arrayList_notifications_time = new ArrayList<>();
        arrayList_notifications_time.addAll(times);
        arrayListAdapter_notifications_time = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_notifications_time);
        notificationTimeInput.setAdapter(arrayListAdapter_notifications_time);
        notificationTimeInput.setThreshold(1);

        // Dummy Values
        addValues();
    }

    /**
     * Handler for the add obstacle value
     */
    public void onCLickHandler(){
        newTitle = obstacleTitleEditText.getEditableText().toString();
        newDescription = obstacleDescEditText.getEditableText().toString();
        selectedValue = (valuesTextInputLayout.getEditText()).getText().toString();
        notification_time_value = timeHours.get((notificationTimeInputLayout.getEditText()).getText().toString());
        addObstacle(selectedValue, notification_time_value, importance);
        startActivity(new Intent(NewObstacleActivity.this, ObstaclesActivity.class));
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
     * Sets up check boxes by adding on click listeners
     */
    public void setUpCheckBoxes(){
        checkBoxObjects.put((CheckBox) findViewById(R.id.mondayBox), "1");
        checkBoxObjects.put((CheckBox) findViewById(R.id.tuesdayBox), "2");
        checkBoxObjects.put((CheckBox) findViewById(R.id.wedBox), "3");
        checkBoxObjects.put((CheckBox) findViewById(R.id.thursBox), "4");
        checkBoxObjects.put((CheckBox) findViewById(R.id.friBox), "5");
        checkBoxObjects.put((CheckBox) findViewById(R.id.satBox), "6");
        checkBoxObjects.put((CheckBox) findViewById(R.id.sunBox), "7");

        for (CheckBox box : checkBoxObjects.keySet()){
            box.setOnClickListener(view -> {
                getCheckBox(box);
            });
        }
    }

    public void getCheckBox(CheckBox box){
        if (box.isChecked()) {
            days_data += checkBoxObjects.get(findViewById(box.getId()));
        }
    }

    public void addValues(){
        // this is for testing purposes while we don't have any inserted values from the user
        Value testValue = new Value("fit3162", "education");
        mThriveViewModel.insert(testValue);
        Value testValue2 = new Value("fit3122", "education");
        mThriveViewModel.insert(testValue2);
    }

    /**
     * This method handles adding a new obstacle in the database.
     * @param value name of the related value from dropdown
     * @param time time for the notification to occur at
     * @param importance the int representing the importance of the obstacle.
     */
    public void addObstacle(String value, Integer time, Integer importance){
        try {
            Obstacle obs1 = new Obstacle(newTitle, newDescription, time == null, time, 0, Integer.parseInt(days_data));
            mThriveViewModel.insert(obs1);
            //inserting a related value and obstacle to the Obstacle_value table
            Obstacle_value obstacle_value = new Obstacle_value(newTitle, value);
            mThriveViewModel.insert(obstacle_value);
            Toast.makeText(getApplicationContext(),
                    "New Value: " + newTitle + " added." ,
                    Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.i("RESPONSE", e.toString());
        }
    }

}
