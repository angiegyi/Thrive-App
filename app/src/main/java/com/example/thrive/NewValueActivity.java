package com.example.thrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.thrive.Database.ThriveViewModel;
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

public class NewValueActivity extends AppCompatActivity{

    Button createButton;
    TextInputEditText inputValue;
    TextInputLayout inputCat;
    Slider inputImportance;
    String newValue;
    String newCat;
    String newImportance;
    ThriveViewModel tvm;
    AutoCompleteTextView catOptions;
    ArrayList<String> catList = new ArrayList<>();

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from new_value xml
        setContentView(R.layout.new_value);

        // Initialise the drop down
        initDropDown();

        // Initialise create value button
        initCreateButton();

    }


    // Initialising the drop down box with categories
    private void initDropDown(){
        // Linking to XML
        catOptions = findViewById(R.id.categoryOptions);

        // Getting categories from the database
        tvm = new ViewModelProvider(this).get(ThriveViewModel.class);
        tvm.getAllCategories().observe(this, categories -> {

            // Iterate through each value in the valData that's returned
            for (Object obj : categories){
                // add each value to the arraylist
                try {
                    catList.add(objectToJSONObject(obj).getString("name"));
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.dropdown_item, catList.toArray());

        catOptions.setAdapter(adapter);
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

    // Initialising create button to  value page
    private void initCreateButton(){
        createButton = findViewById(R.id.createValueButton);
        // If button is clicked, the values activity is shown
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Finding XML elements
                inputValue = findViewById(R.id.valueNameText);
                inputCat = findViewById(R.id.categoryInput);
                inputImportance = findViewById(R.id.importanceSlider);

                // Converting to strings
                newValue = inputValue.getEditableText().toString();
                newCat = inputCat.getEditText().getText().toString();

                // Creating value object
                Value val = new Value(newValue, newCat);
                tvm.insert(val);
                // Inserting the value category
                // Value_Category valCat = new Value_Category(newCat);
                // Need way to add category value
                // tvm.insert(valCat);

                //Move to values activity page
                Intent intent = new Intent(NewValueActivity.this, ValuesActivity.class);
                startActivity(intent);
            }
        });
    }

}
