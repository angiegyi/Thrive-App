package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HooksStoryOnboarding extends AppCompatActivity {

    Button nextButton;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from hooks story xml
        setContentView(R.layout.onboarding_story_hooks);

        // Init next button
        initNext();

        setTitle("Discovering your hook behaviours");
    }


    // Initialising next button
    private void initNext(){
        nextButton = findViewById(R.id.next_button_hs);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the next page
                Intent intent = new Intent(HooksStoryOnboarding.this, HooksViewOnboarding.class);
                startActivity(intent);
            }
        });
    }

}
