package com.example.thrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;

public class BoatOnboarding extends AppCompatActivity {

    Button nextButton;
    ThriveViewModel mThriveViewModel;
    SharedPreferences sp;
    String SHARED_PREFERENCE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boat_onboarding);
        setTitle("The Boat");

        nextButton = findViewById(R.id.BoatMaterialButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoatOnboarding.this, MainActivity.class));
            }
        });
        initData();
    }

    protected void initData(){
        SHARED_PREFERENCE_NAME ="MyUserPrefs";
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sp.edit();
        editor.putBoolean("moodChecked", false);
        editor.apply();
    }
}
