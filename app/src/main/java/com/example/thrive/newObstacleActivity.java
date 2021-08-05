package com.example.thrive;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class newObstacleActivity extends AppCompatActivity {

    TextInputLayout obstacleNameTextField;

    TextInputLayout categoryTextInputLayout;
    AutoCompleteTextView categoryInput;
    ArrayList<String> arrayList_category;
    ArrayAdapter<String> arrayListAdapter_category;

    TextInputLayout valuesTextInputLayout;
    AutoCompleteTextView valuesInput;

    TextInputLayout notifFreqLayout;
    AutoCompleteTextView notificationInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_obstacle);

        obstacleNameTextField = findViewById(R.id.obstacleNameTextField);

        categoryTextInputLayout = findViewById(R.id.categoryTextInputLayout);
        categoryInput = findViewById(R.id.categoryInput);

        valuesTextInputLayout = findViewById(R.id.valuesTextInputLayout);
        valuesInput = findViewById(R.id.valuesInput);

        notifFreqLayout = findViewById(R.id.notifFreqLayout);
        notificationInput = findViewById(R.id.notificationInput);

        arrayList_category = new ArrayList<>();
        arrayList_category.add("hello");
        arrayList_category.add("hello");
        arrayList_category.add("hello");
        arrayListAdapter_category = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_category);
        categoryInput.setAdapter(arrayListAdapter_category);
        categoryInput.setThreshold(1);
    }

}
