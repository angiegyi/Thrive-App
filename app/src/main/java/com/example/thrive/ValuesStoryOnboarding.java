package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ValuesStoryOnboarding extends AppCompatActivity {
    Button nextButton;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Take from values story xml
        setContentView(R.layout.onboarding_story_values);

        // Init next button
        initNext();
    }


    // Initialising next button to values insert page
    private void initNext(){
        nextButton = findViewById(R.id.next_button);
        // If button is clicked
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to the values insert page
                Intent intent = new Intent(ValuesStoryOnboarding.this, ValuesViewOnboarding.class);
                startActivity(intent);
            }
        });
    }


}
