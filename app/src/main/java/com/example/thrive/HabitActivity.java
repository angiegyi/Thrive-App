package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HabitActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habits);
        onClickFab();
    }


    private void onClickFab(){
        fab = findViewById(R.id.fab_new_habit);
        fab.setOnClickListener(view -> startActivity(new Intent(HabitActivity.this, NewHabitActivity.class)));
    }
}
