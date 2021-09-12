package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HabitsStoryOnboarding extends AppCompatActivity {

    Button nextButton;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from habits story xml
        setContentView(R.layout.onboarding_story_habits);

        // Init next button
        initNext();
    }


    // Initialising next button to habits insert
    private void initNext(){
        nextButton = findViewById(R.id.next_button_habits);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the next page
                Intent intent = new Intent(HabitsStoryOnboarding.this, HabitsViewOnboarding.class);
                startActivity(intent);
            }
        });
    }

}
