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
import com.example.thrive.Database.entities.Value_Category;
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


public class NewValueActivity extends AppCompatActivity{

    // DB Model
    ThriveViewModel mThriveViewModel;

    // Values Title
    TextInputLayout valueTitleTextLayout;
    TextInputEditText valueTitleEditText;

    /*
    String newTitle;

    // Values Description
    TextInputLayout valueDescTextLayout;
    TextInputEditText vDescEditText;
    String newDescription;

    // Value
    TextInputLayout valuesTextInputLayout;
    AutoCompleteTextView valuesInput;
    ArrayList<String> arrayList_values;
    ArrayAdapter<String> arrayListAdapter_values;
    String selectedValue;

    // Button
    Button createValueButton;

     */
    // Importance
    Slider slider;
    Integer importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_value);

        // Slider
        slider = findViewById(R.id.slider);
        slider.addOnChangeListener((slider, value, fromUser) -> importance = Float.floatToIntBits(value));

        /*
        // Button
        createValueButton = findViewById(R.id.createValueButton);
        createValueButton.setOnClickListener(v -> onCLickHandler());

        // Value Title
        valueTitleTextLayout = findViewById(R.id.valueNameTextField);
        valueTitleEditText = findViewById(R.id.valueNameEditLayout);

        // Value Description
        valueDescTextLayout = findViewById(R.id.valueDescTextField);
        valueDescEditText = findViewById(R.id.valueDescEditLayout);

        // Need to add & pull categories
        */
    }

    /**
     * Handler for the add obstacle value
     */
    public void onCLickHandler(){
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


}
