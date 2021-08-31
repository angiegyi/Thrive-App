package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StoryEndOnboarding extends AppCompatActivity {
    Button nextButton;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from onboarding xml
        setContentView(R.layout.onboarding_story_end);

        // Init next button
        initNext();
    }


    // Initialising next button to values story page
    private void initNext(){
        nextButton = findViewById(R.id.next_button);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the values story page
                Intent intent = new Intent(StoryEndOnboarding.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
