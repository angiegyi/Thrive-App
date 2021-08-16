package com.example.thrive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.Value_Category;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



public class NewValueActivity extends AppCompatActivity{

    Button createButton;

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Take from new_value xml
        setContentView(R.layout.new_value);

        // Initialise create value button
        initCreateButton();
    }


    // Initialising create button to  value page
    private void initCreateButton(){
        createButton = findViewById(R.id.createValueButton);
        // If button is clicked, the values activity is shown
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Move to values activity page
                Intent intent = new Intent(NewValueActivity.this, valuesActivity.class);
                startActivity(intent);
            }
        });
    }

}
