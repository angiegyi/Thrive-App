package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MountainOnboarding extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_mountain);

        nextButton = findViewById(R.id.MountainMaterialButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MountainOnboarding.this, LakeOnboarding.class));

            }
        });
    }

    public void toNextPage(View view){
        startActivity(new Intent(com.example.thrive.MountainOnboarding.this, ValuesActivity.class));
    }
}
