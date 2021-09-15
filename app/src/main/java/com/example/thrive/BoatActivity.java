package com.example.thrive;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;

public class BoatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boat);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Tools");

        ConstraintLayout progressLayout = findViewById(R.id.myProgressLayout);
        progressLayout.setOnClickListener(view -> onClickProgress());
    }

    private void onClickProgress(){
        startActivity(new Intent(BoatActivity.this, ProgressActivity.class));
    }
}
