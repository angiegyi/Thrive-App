package com.example.thrive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;

public class SplashScreen extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    ThriveViewModel mThriveViewModel;
    SharedPreferences sp;
    String SHARED_PREFERENCE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        initData();

        // splash screen
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    protected void initData(){
        SHARED_PREFERENCE_NAME ="MyUserPrefs";
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sp.edit();
        editor.putBoolean("moodChecked", false);
        editor.apply();
        boolean ok = sp.getBoolean("moodChecked", false);
    }
}
