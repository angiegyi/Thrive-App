package com.example.thrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toValuesPage(View view){
        startActivity(new Intent(MainActivity.this, valuesActivity.class));
    }

    public void toObstaclesPage(View view){
        startActivity(new Intent(MainActivity.this, ObstaclesActivity.class));
    }

    public void toHabitsPage(View view){
        startActivity(new Intent(MainActivity.this, HabitActivity.class));
    }

    public void toHooksPage(View view){
        startActivity(new Intent(MainActivity.this, HookBehaviours.class));
    }
}