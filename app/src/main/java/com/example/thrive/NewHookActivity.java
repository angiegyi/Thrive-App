package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
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

public class NewHookActivity extends AppCompatActivity {

    // DB Model
    ThriveViewModel mThriveViewModel;

    // Hook Title
    TextInputLayout hookTitleTextLayout;
    TextInputEditText hookTitleEditText;
    String newTitle;

    // Hook Obstacle
    Button newHook;
    String obstacleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_hook);
        setTitle("New Hook");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            obstacleName = extras.getString("name");
        }

        // Hook Title
        hookTitleTextLayout = findViewById(R.id.hookTitleEditLayout);
        hookTitleEditText = findViewById(R.id.hookTitleTextField);

        // button
        newHook = findViewById(R.id.createHookButton);
        newHook.setOnClickListener(view -> onClickListener());
    }

    public void onClickListener(){
        // Error checking for title

        if (TextUtils.isEmpty(hookTitleEditText.getText())){
            hookTitleEditText.setError( "Hook title is required!" );
        }
        else {
            newTitle = hookTitleEditText.getEditableText().toString();
            addHook();
            Intent intent = new Intent(NewHookActivity.this,HookBehaviours.class);
            intent.putExtra("name", obstacleName);
            startActivity(intent);
        }
    }

    public void addHook(){
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        try {
            Hook hook = new Hook(newTitle, obstacleName);
            mThriveViewModel.insert(hook);
            Toast.makeText(getApplicationContext(),
                    "New Hook: " + newTitle + " added." ,
                    Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.i("RESPONSE", e.toString());
        }
    }
}
