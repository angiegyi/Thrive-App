package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;
import org.json.JSONException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class valuesActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView displayList;
    // String[] testList = {"first","second","third","other"};
    ArrayList<String> valuesList = new ArrayList<>();

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values xml
        setContentView(R.layout.values);

        // Initialise list data
        initList();

        // Init floating action button
        initFab();
    }

    private void initList(){
        displayList = findViewById(R.id.valuesList);

        ThriveViewModel tvm = new ViewModelProvider(this).get(ThriveViewModel.class);
        tvm.getAllValues().observe(this, valData -> {

            // Iterate through each value in the valData that's returned
            for (Object obj : valData){
                // add each value to the arraylist
                try {
                    valuesList.add(objectToJSONObject(obj).get("name").toString());
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, valuesList.toArray());

        displayList.setAdapter(adapter);
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

    // Initialising action button to new value page
    private void initFab(){
        fab = findViewById(R.id.fabNewVal);
        // If fab button is clicked, the add values activity is shown
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to values activity page
                Intent intent = new Intent(valuesActivity.this, NewValueActivity.class);
                startActivity(intent);
            }
        });
    }

}
