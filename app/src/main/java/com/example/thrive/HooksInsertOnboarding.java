package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Hook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HooksInsertOnboarding extends AppCompatActivity {

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
    Button newHook;

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
        mThriveViewModel.getAllObstacles().observe(this, newData -> {
            for (Object obj : newData) {
                try {
                    arrayList_obstacles.add(objectToJSONObject(obj).get("obstacleName").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        arrayListAdapter_obstacles = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_obstacles);
        hookInput.setAdapter(arrayListAdapter_obstacles);
        hookInput.setThreshold(1);

        // button
        newHook = findViewById(R.id.createHookButton);
        newHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener();
            }
        });
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

    public void onClickListener(){
        // Error checking for title

        if (TextUtils.isEmpty(hookTitleEditText.getText()) || TextUtils.isEmpty(hookTextInputLayout.getEditText().getText())){
            if( TextUtils.isEmpty(hookTitleEditText.getText())){
                hookTitleEditText.setError( "Hook title is required!" );
            }
            // Error Checking Obstacle
            if (TextUtils.isEmpty(hookTextInputLayout.getEditText().getText())){
                hookTextInputLayout.setError( "Related Obstacle is required!" );
            }
        }
        else {
            newTitle = hookTitleEditText.getEditableText().toString();
            selectedObstacle = (hookTextInputLayout.getEditText()).getText().toString();
            addHook();
            startActivity(new Intent(HooksInsertOnboarding.this, HooksViewOnboarding.class));
        }
    }

    public void addHook(){
        try {
            Hook hook = new Hook(newTitle, selectedObstacle);
            mThriveViewModel.insert(hook);
            Toast.makeText(getApplicationContext(),
                    "New Hook: " + newTitle + " added." ,
                    Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.i("RESPONSE", e.toString());
        }
    }


}
