package com.example.thrive;

import android.content.Intent;
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

public class HooksViewOnboarding extends AppCompatActivity {

    Button addHooksButton;
    Button nextButton;
    ListView displayList;
    ThriveViewModel tvm;
    ArrayAdapter adapter;
    ArrayList<String> hooksListOut = new ArrayList<>();

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values xml
        setContentView(R.layout.onboarding_view_hooks);

        // Init add values button
        initAddHooks();

        // Init next button
        initNext();

        // Init all values
        initList();

        setTitle("Discovering your hook behaviours");

    }

    private void initList(){
        displayList = findViewById(R.id.hooksOnboardList);

        tvm = new ViewModelProvider(this).get(ThriveViewModel.class);
        tvm.getAllHooks().observe(this, newData -> {
            for (Object obj : newData) {
                if (obj != null){
                    try {
                        hooksListOut.add(objectToJSONObject(obj).getString("hook_behaviour"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, hooksListOut.toArray());
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
    private void initAddHooks(){
        addHooksButton = findViewById(R.id.add_hooks_button);
        // If button is clicked
        addHooksButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the values insert page
                Intent intent = new Intent(HooksViewOnboarding.this, HooksInsertOnboarding.class);
                startActivity(intent);
            }
        });
    }

    // Initialising next button to the next page
    private void initNext(){
        nextButton = findViewById(R.id.next_button_hooksi);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the next page
                Intent intent = new Intent(HooksViewOnboarding.this, StoryEndOnboarding.class);
                startActivity(intent);
            }
        });
    }

}
