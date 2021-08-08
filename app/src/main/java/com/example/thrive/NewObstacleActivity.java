package com.example.thrive;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewObstacleActivity extends AppCompatActivity {

    ThriveViewModel mThriveViewModel;

    TextInputLayout obstacleNameTextField;
    TextInputEditText obstacleTitleEditText;
    String newTitle;

    TextInputLayout obstacleNameDescField;
    TextInputEditText obstacleDescEditText;
    String newDescription;

    TextInputLayout valuesTextInputLayout;
    AutoCompleteTextView valuesInput;
    ArrayList<String> arrayList_values;
    ArrayAdapter<String> arrayListAdapter_values;
    String selectedValue;

    // Time
    TextInputLayout notifTimeInputLayout;
    AutoCompleteTextView notificationTimeInput;
    ArrayList<String> arrayList_notifications_time;
    ArrayAdapter<String> arrayListAdapter_notifications_time;
    Integer notification_time_value;
    List<String> times = Arrays.asList("12:00 AM", "1:00 AM","2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM"
    , "1:00 PM","2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM");

    Map<String, Integer> timeHours = new HashMap<String, Integer>();

    // Days
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    String days_data;

    // Button
    Button createObstacleButton;

    // Importance
    Slider slider;
    Integer importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_obstacle);

        // Initalise time hashmap
        for (int i = 0; i < 25; i++ ){
            timeHours.put(times.get(i), i);
        }

        // Slider
        slider = findViewById(R.id.slider);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                importance = Float.floatToIntBits(value);
            }
        });

        // Boxes
        setUpCheckBoxes();

        // Button
        createObstacleButton = findViewById(R.id.createObstacleButton);
        createObstacleButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                onCLickHandler();
            }
        });

        // Name of Obstacle
        obstacleNameTextField = findViewById(R.id.obstacleNameTextField);
        obstacleTitleEditText = findViewById(R.id.obstacleNameEditLayout);
        newTitle = obstacleTitleEditText.getText().toString();

        // Obstacle Description
        obstacleNameDescField = findViewById(R.id.obstacleDescTextField);
        obstacleDescEditText = findViewById(R.id.obstacleDescEditLayout);
        newDescription = obstacleDescEditText.getText().toString();

        // Values -> need to add values to database
        valuesTextInputLayout = findViewById(R.id.valuesTextInputLayout);
        valuesInput = findViewById(R.id.valuesInput);
        arrayList_values = new ArrayList<>();
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        mThriveViewModel.getAllValues().observe(this, newData -> {
            for (Object obj : newData) {
                arrayList_values.add(obj.toString());
            }
        });
        arrayListAdapter_values = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_values);
        valuesInput.setAdapter(arrayListAdapter_values);
        valuesInput.setThreshold(1);

        // Notifications -> Time
        notifTimeInputLayout = findViewById(R.id.notifTimeInput);
        notificationTimeInput = findViewById(R.id.notificationTimeInput);
        arrayList_notifications_time = new ArrayList<>();
        arrayList_notifications_time.addAll(times);
        arrayListAdapter_notifications_time = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_notifications_time);
        notificationTimeInput.setAdapter(arrayListAdapter_notifications_time);
        notificationTimeInput.setThreshold(1);
    }

    public void onCLickHandler(){
        selectedValue = (valuesTextInputLayout.getEditText()).getText().toString();
        notification_time_value = timeHours.get((notifTimeInputLayout.getEditText()).getText().toString());
//        addObstacle(categoryValue, selectedValue, notification_time_value, importance);
    }

    public void setUpCheckBoxes(){
        checkBoxes.add(findViewById(R.id.mondayBox));
        checkBoxes.add(findViewById(R.id.tuesdayBox));
        checkBoxes.add(findViewById(R.id.wedBox));
        checkBoxes.add(findViewById(R.id.thursBox));
        checkBoxes.add(findViewById(R.id.friBox));
        checkBoxes.add(findViewById(R.id.satBox));
        checkBoxes.add(findViewById(R.id.sunBox));

        for (CheckBox box : checkBoxes){
            box.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               days_data = "";
               // Monday
               if (checkBoxes.get(0).isSelected()){
                   days_data += "1";
               }
               else if (checkBoxes.get(1).isSelected()){
                   days_data += "2";
               }
               else if (checkBoxes.get(2).isSelected()){
                   days_data += "3";
               }
               else if (checkBoxes.get(3).isSelected()){
                   days_data += "4";
               }
               else if (checkBoxes.get(4).isSelected()){
                   days_data += "5";
               }
               else if (checkBoxes.get(5).isSelected()){
                   days_data += "6";
               }
               else if (checkBoxes.get(6).isSelected()){
                   days_data += "6";
               }
           }});
        }
    }

    public void addObstacle(String category, String value, Integer time, Integer importance){
        // convert days data to int
        // add check for time
//      obstacle o = new obstacle(newTitle, "", time == null, time, 0, days_data);
//      mThriveViewModel.insert(o);
      // create new value
    }

}
