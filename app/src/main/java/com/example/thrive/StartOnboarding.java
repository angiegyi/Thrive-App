package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartOnboarding extends AppCompatActivity {

    Button nextButton;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values xml
        setContentView(R.layout.onboarding_story_start);

        // Init floating action button
        initNext();
    }


    // Initialising action button to new value page
    private void initNext(){
        nextButton = findViewById(R.id.next_button);
        // If fab button is clicked, the add values activity is shown
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to values activity page
                Intent intent = new Intent(StartOnboarding.this, ValuesStoryOnboarding.class);
                startActivity(intent);
            }
        });
    }
}
