package com.example.thrive;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.Mood;

import java.util.List;

public class valuesActivity extends AppCompatActivity {
    ThriveViewModel mThriveViewModel;
    private LiveData<List<Category>> mAllCat;
    TextView tv_cat;
    TextView tv_moods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.values);
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        tv_cat = findViewById(R.id.tvCategory);
        mThriveViewModel.getAllCategories().observe(this, newData -> {
            Category cat;
            String categories = "";
            for(int i =0; i<newData.size(); i++) {
                cat = newData.get(i);
                categories+= cat.getName()+"\n";
            }
            tv_cat.setText(categories.toString());
        });


        tv_moods = findViewById(R.id.tvMoods);
        mThriveViewModel.getAllMoods().observe(this, newData -> {
            Mood mood;
            String moods = "";
            for(int i =0; i<newData.size(); i++) {
                mood = newData.get(i);
                moods+= mood.getMood_name()+"\n";
            }
            tv_moods.setText(moods.toString());
        });




    }
}
