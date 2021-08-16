package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class valuesActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView displayList;
    String[] testList = {"first","second","third","other"};

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

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, testList);

        displayList.setAdapter(adapter);
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
