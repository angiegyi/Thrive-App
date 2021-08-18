package com.example.thrive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog checkInDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewCheckInDialog();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
//        createNewCheckInDialog();
    }

    public void toValuesPage(View view){
        startActivity(new Intent(MainActivity.this, valuesActivity.class));
    }

    public void toObstaclesPage(View view){
        startActivity(new Intent(MainActivity.this, ObstaclesActivity.class));
    }

    public void toBoatPage(View view) {
        startActivity(new Intent(MainActivity.this, BoatActivity.class));
    }

    public void toHabitsPage(View view){
        startActivity(new Intent(MainActivity.this, HabitActivity.class));
    }

    public void toHooksPage(View view){
        startActivity(new Intent(MainActivity.this, HookBehaviours.class));
    }

    public void createNewCheckInDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View checkInPopUpView = getLayoutInflater().inflate(R.layout.check_in_pop_up, null);
        dialogBuilder.setView(checkInPopUpView);
        checkInDialog = dialogBuilder.create();
        checkInDialog.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(checkInDialog.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth * 0.9f);
        int dialogWindowHeight = (int) (displayHeight * 0.9f);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        checkInDialog.getWindow().setAttributes(layoutParams);
    }
}