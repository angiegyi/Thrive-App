package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toValuesPage(View view){
        startActivity(new Intent(HomeScreen.this, valuesActivity.class));
    }

    public void toObstaclesPage(View view){
        startActivity(new Intent(HomeScreen.this, ObstaclesActivity.class));
    }

    public void toHabitsPage(View view){
        startActivity(new Intent(HomeScreen.this, HabitActivity.class));
    }

    public void toHooksPage(View view){
        startActivity(new Intent(HomeScreen.this, HookBehaviours.class));
    }
}