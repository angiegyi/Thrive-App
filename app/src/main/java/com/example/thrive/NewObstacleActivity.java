package com.example.thrive;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class NewObstacleActivity extends AppCompatActivity {

    ThriveViewModel mThriveViewModel;

    TextInputLayout obstacleNameTextField;
    TextInputEditText obstacleTitleEditText;
    String newTitle;

    TextInputLayout categoryTextInputLayout;
    AutoCompleteTextView categoryInput;
    ArrayList<String> arrayList_category;
    ArrayAdapter<String> arrayListAdapter_category;
    String categoryValue;

    TextInputLayout valuesTextInputLayout;
    AutoCompleteTextView valuesInput;
    ArrayList<String> arrayList_values;
    ArrayAdapter<String> arrayListAdapter_values;
    String selectedValue;

    TextInputLayout notifFreqLayout;
    AutoCompleteTextView notificationInput;
    ArrayList<String> arrayList_notifications;
    ArrayAdapter<String> arrayListAdapter_notifications;
    String notificationValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_obstacle);

        // Name of Obstacle
        obstacleNameTextField = findViewById(R.id.obstacleNameTextField);
        obstacleTitleEditText = findViewById(R.id.obstacleTitleEditText);
        newTitle = obstacleTitleEditText.getText().toString();

        // Values -> need to add values to database
        valuesTextInputLayout = findViewById(R.id.valuesTextInputLayout);
        valuesInput = findViewById(R.id.valuesInput);
        arrayList_values = new ArrayList<>();
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        mThriveViewModel.getmAllValues().observe(this, newData -> {
            for (Object obj : newData) {
                arrayList_values.add(obj.toString());
            }
        });
        arrayListAdapter_values = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_values);
        valuesInput.setAdapter(arrayListAdapter_values);
        valuesInput.setThreshold(1);
        selectedValue =(valuesTextInputLayout.getEditText()).getText().toString();

        // Notifications
        notifFreqLayout = findViewById(R.id.notifFreqLayout);
        notificationInput = findViewById(R.id.notificationInput);
        arrayList_notifications = new ArrayList<>();
        arrayList_notifications.add("Once a Day");
        arrayList_notifications.add("Twice a Day");
        arrayList_notifications.add("3 days a week");
        arrayList_notifications.add("Once a week");
        arrayList_notifications.add("Never");

        arrayListAdapter_category = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_category);
        notificationInput.setAdapter(arrayListAdapter_notifications);
        notificationInput.setThreshold(1);
        notificationValue =(notifFreqLayout.getEditText()).getText().toString();

        // Category Stuff
        categoryTextInputLayout = findViewById(R.id.categoryTextInputLayout);
        categoryInput = findViewById(R.id.categoryInput);
        arrayList_category = new ArrayList<>();
        arrayList_category.add("health");
        arrayList_category.add("well-being");
        arrayList_category.add("study");

        arrayListAdapter_category = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_category);
        categoryInput.setAdapter(arrayListAdapter_category);
        categoryInput.setThreshold(1);
        categoryValue =(categoryTextInputLayout.getEditText()).getText().toString();
    }

    public void addObstacle(){
        // Obstacle o = new Obstacle(newTitle, categoryValue, selectedValue, notificationValue);
//        mThriveViewModel.insert(o);
    }

}
