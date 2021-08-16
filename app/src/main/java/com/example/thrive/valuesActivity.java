package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class valuesActivity extends AppCompatActivity {
    FloatingActionButton fab;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Take from values xml
        setContentView(R.layout.values);
        // Init floating action button
        initFab();
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
