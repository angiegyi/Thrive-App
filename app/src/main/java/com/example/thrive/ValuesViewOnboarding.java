package com.example.thrive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ValuesViewOnboarding extends AppCompatActivity {

    Button addValuesButton;
    Button nextButton;
    ListView displayList;
    ThriveViewModel tvm;
    ArrayList<String> valuesListOut = new ArrayList<>();
    SharedPreferences sp;
    String SHARED_PREFERENCE_NAME;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values xml
        setContentView(R.layout.onboarding_view_values);

        // Init add values button
        initAddValues();

        // Init next button
        initNext();

        // Init all values
        initList();

    }

    private void initList(){
        displayList = findViewById(R.id.valuesList2);

        tvm = new ViewModelProvider(this).get(ThriveViewModel.class);
        tvm.getAllValues().observe(this, valData -> {

            // Iterate through each value in the valData that's returned
            for (Object obj : valData){
                // add each value to the arraylist
                try {
                    valuesListOut.add(objectToJSONObject(obj).getString("name"));
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }

            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, valuesListOut.toArray());

            displayList.setAdapter(adapter);
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

    // Initialising next button to values insert page
    private void initAddValues(){
        addValuesButton = findViewById(R.id.add_values_button);
        // If button is clicked
        addValuesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the values insert page
                Intent intent = new Intent(ValuesViewOnboarding.this, ValuesInsertOnboarding.class);
                startActivity(intent);
            }
        });
    }

    // Initialising next button to the next page
    private void initNext(){
        nextButton = findViewById(R.id.next_button_vi);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the next page
                Intent intent = new Intent(ValuesViewOnboarding.this, HabitsStoryOnboarding.class);
                startActivity(intent);
            }
        });
    }


}
