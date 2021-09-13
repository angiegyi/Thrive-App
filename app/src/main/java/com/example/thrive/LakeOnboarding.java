package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LakeOnboarding extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lake_onboarding);
        setTitle("The Lake");

        nextButton = findViewById(R.id.LakeMaterialButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LakeOnboarding.this, BoatOnboarding.class));
            }
        });
    }

}
