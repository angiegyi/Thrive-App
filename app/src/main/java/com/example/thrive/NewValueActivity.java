package com.example.thrive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewValueActivity extends AppCompatActivity{

    // Initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Take from new_value xml
        setContentView(R.layout.new_value);
    }

}
