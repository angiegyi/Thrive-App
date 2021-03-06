package com.example.thrive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thrive.Boat.BoatActivity;
import com.example.thrive.CheckIn.Recommendation;
import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.CheckIn;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.ValueProgress;
import com.google.android.material.slider.Slider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private AlertDialog checkInDialog;
    private View checkInPopUpView;
    ThriveViewModel mThriveViewModel;
    SharedPreferences sp;
    String SHARED_PREFERENCE_NAME;
    private Button help_button;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Thrive Island");
        // add some conditional statement to check if we need to do start onboarding
        SHARED_PREFERENCE_NAME ="MyUserPrefs";
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sp.edit();
        editor.putString("mood", "none");
        editor.apply();
        startMoodTracker();
        startOnBoarding();
        help_button = findViewById(R.id.helpButton);
        help_button.setOnClickListener(view -> helpButtonCallback());
        createValueProgress();
    }

    public void startOnBoarding(){
        SharedPreferences get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean onboarded = get_data.getBoolean("onboarded", false);
        if(!onboarded){
            startActivity(new Intent(MainActivity.this, StartOnboarding.class));
        }
    }

    public void startMoodTracker(){
        SharedPreferences get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean moodChecked = get_data.getBoolean("moodChecked", false);
        Log.i("test", "startMoodTracker: " + moodChecked);
        if(!moodChecked){
            sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = sp.edit();
            editor.putBoolean("moodChecked", true);
            editor.apply();
            createNewCheckInDialog();
        }
    }

    public void helpButtonCallback(){
        startActivity(new Intent(MainActivity.this, SunOnboarding.class));
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sp.edit();
        editor.putBoolean("onboarded", true);
        editor.apply();
        Log.i("test", "main: onStop()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        createValueProgress();
    }

    public void toValuesPage(View view){
        Toast.makeText(getApplicationContext(),
                "Clicked Sun, opening Values",
                Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, ValuesActivity.class));
    }

    public void toObstaclesPage(View view){
        Toast.makeText(getApplicationContext(),
                "Clicked Lake, opening Obstacles",
                Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, ObstaclesActivity.class));
    }

    public void toBoatPage(View view) {
        Toast.makeText(getApplicationContext(),
                "Clicked Boat, opening Tools",
                Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, BoatActivity.class));
    }

    public void toHabitsPage(View view){
        Toast.makeText(getApplicationContext(),
                "Clicked Mountain, opening Valued Behaviours",
                Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, HabitActivity.class));
    }

    public boolean checkOnboarding(){
        // Get onboarding status
        SharedPreferences get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean onboarded = get_data.getBoolean("onboarded", false);
        return onboarded;
    }

    public void createNewCheckInDialog(){
        /*
        Building Dialog box for CheckIn
         */
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        checkInPopUpView = getLayoutInflater().inflate(R.layout.check_in_pop_up, null);
        dialogBuilder.setView(checkInPopUpView);
        checkInDialog = dialogBuilder.create();
        checkInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        checkInDialog.setCancelable(false); //prevent dialog box from getting dismissed by back button
        checkInDialog.setCanceledOnTouchOutside(false); // prevent dialog from getting dismissed on outside touch
        checkInDialog.show();

        /*
        Getting selection from Radio Group
         */
        RadioGroup radioGroup = (RadioGroup) checkInDialog.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor = sp.edit();
                SharedPreferences get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                String mood_value = get_data.getString("mood", "none");
                switch (checkedId){
                    case R.id.sadRadioButton:
                        // get negative moods
                        editor.putString("mood", "negative");
                        editor.apply();
                        get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                        mood_value = get_data.getString("mood", "none");
                        //Toast.makeText(getApplicationContext(), "negative", Toast.LENGTH_SHORT).show();
                         break;
                    case R.id.neutralRadioButton:
                        editor.putString("mood", "neutral");
                        editor.apply();
                        //Toast.makeText(getApplicationContext(), "neutral", Toast.LENGTH_SHORT).show();
                        get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                        mood_value = get_data.getString("mood", "none");
                        break;
                    case R.id.happyRadioButton:
                        // get positive moods
                        editor.putString("mood", "positive");
                        editor.apply();
                        //Toast.makeText(getApplicationContext(), "positive", Toast.LENGTH_SHORT).show();
                        get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                        mood_value = get_data.getString("mood", "none");
                        break;
                }
            }
        });

        /*
        Next Dialog #CheckIn2
         */
        View nextButtonCheckIn = checkInDialog.findViewById(R.id.btn_next);
        nextButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                String mood_value = get_data.getString("mood", "none");
                if (!mood_value.equals("none")){
                    showCheckInDialog2();
                } else {
                    Toast.makeText(getApplicationContext(), "Please choose a face", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showCheckInDialog2(){
        checkInDialog.dismiss();
        ((ViewGroup)checkInPopUpView.getParent()).removeView(checkInPopUpView);
        checkInDialog.dismiss();
        AlertDialog.Builder dialogBuilder2 = new AlertDialog.Builder(this);
        View checkInPopUpView2 = getLayoutInflater().inflate(R.layout.check_in_pop_up2, null);
        dialogBuilder2.setView(checkInPopUpView2);
        AlertDialog checkInDialog2 = dialogBuilder2.create();
        checkInDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        checkInDialog2.setCancelable(false); //prevent dialog box from getting dismissed by back button
        checkInDialog2.setCanceledOnTouchOutside(false); // prevent dialog from getting dismissed on outside touch
        checkInDialog2.show();
        AutoCompleteTextView relatedMoodsDropDown = (AutoCompleteTextView) checkInDialog2.findViewById(R.id.relatedMoodsInput);
        ArrayList<String> moods_array= new ArrayList<>();
        ArrayAdapter<String> arrayListAdapter;

        int value_of_mood;
        SharedPreferences get_data = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String mood_value = get_data.getString("mood", "neutral");
        if (mood_value.equals("negative")){
            value_of_mood = 0;
        } else if (mood_value.equals("positive")){
            value_of_mood = 1;
        } else {
            value_of_mood =2;
        }

        //get data source for adapter
        mThriveViewModel.getMoodType(value_of_mood).observe(this, newData -> {
            Mood mood;
            for(int i =0; i<newData.size(); i++) {
                mood = newData.get(i);
                String name= mood.getMood_name();
                moods_array.add(name);
            }
        });
        arrayListAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, moods_array);
        relatedMoodsDropDown.setAdapter(arrayListAdapter);
       View nextButtonCheckIn = checkInDialog2.findViewById(R.id.nextButtonCheckIn2);
        nextButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String relatedMood = relatedMoodsDropDown.getText().toString();
                if (!relatedMood.equals("")) {
                    checkInDialog2.dismiss();
                    // getting dates for check in
                    SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy", Locale.getDefault());
                    SimpleDateFormat sdf_month = new SimpleDateFormat("MM", Locale.getDefault());
                    SimpleDateFormat sdf_day = new SimpleDateFormat("dd", Locale.getDefault());
                    int currentYear = Integer.parseInt(sdf_year.format(new Date()));
                    int currentMonth = Integer.parseInt(sdf_month.format(new Date()));
                    int currentDay = Integer.parseInt(sdf_day.format(new Date()));
                    CheckIn checkIn = new CheckIn();
                    checkIn.setDate(currentYear, currentMonth, currentDay);
                    EditText editTextReason = checkInDialog2.findViewById(R.id.editTextReason);
                    if (editTextReason != null) {
                        checkIn.setReason(editTextReason.getText().toString());
                    }
                    checkIn.setMood(relatedMood);
                    // create recommendation
                    Recommendation rec = new Recommendation(relatedMood, mThriveViewModel);
                    // set the activity
                    Activity activity = rec.getRecommendation();
                    // inflate the new popup with the recommended activity
                    checkIn.setActivityName(activity.getActivityName());
                    mThriveViewModel.insert(checkIn);
                    showLoadingPopUp(activity);
                    //showRecommendationPopUp(activity);
                } else {
                    Toast.makeText(getApplicationContext(), "Please choose a related mood", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showLoadingPopUp(Activity activity){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View checkInPopUpView = getLayoutInflater().inflate(R.layout.loading_screen_recommendation, null);
        dialogBuilder.setView(checkInPopUpView);
        AlertDialog checkInDialog = dialogBuilder.create();
        checkInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        checkInDialog.show();
        /*
        Finding the animation in animation_loading layout and starting the loading animation
         */
        AnimationDrawable animation;
        ImageView loading = checkInDialog.findViewById(R.id.animation_loading);
        assert loading != null;
        animation = (AnimationDrawable) loading.getDrawable();
        animation.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // close your dialog
                checkInDialog.dismiss();
                showRecommendationPopUp(activity);
            }

        }, 2000); // 2,000 ms delay
    }

    public void showRecommendationPopUp(Activity activity){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View checkInPopUpView = getLayoutInflater().inflate(R.layout.recommendation_pop_up, null);
        dialogBuilder.setView(checkInPopUpView);
        AlertDialog checkInDialog = dialogBuilder.create();
        checkInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        checkInDialog.setCancelable(false); //prevent dialog box from getting dismissed by back button
        checkInDialog.setCanceledOnTouchOutside(false); // prevent dialog from getting dismissed on outside touch
        checkInDialog.show();

        //set activity title
        TextView activityName = checkInDialog.findViewById(R.id.tv_activity);
        activityName.setText(activity.getActivityName());

        // set activity description
        TextView description = checkInDialog.findViewById(R.id.tv_description);
        description.setText(activity.getActivityDescription());
        description.setMovementMethod(new ScrollingMovementMethod());

        // set activityRating
        Slider rating = checkInDialog.findViewById(R.id.ratingSlider);
        rating.setValue(activity.getActivityRating());


        rating.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                //Update user rating
                mThriveViewModel.updateActivityRating(activity.getActivityName(), (int) value);
            }
        });

        View nextButtonCheckIn = checkInDialog.findViewById(R.id.btn_okay);
        nextButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkInDialog.dismiss();
                //update rating
            }
        });
    }

    public void createValueProgress(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        int lastDay = settings.getInt("last_time_started", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastDay) {
            mThriveViewModel.getAllValues().observe(this, newData -> {
                for (Value obj : newData) {
                    if (obj != null){
                        String valueName = obj.getName();
                        ValueProgress valueProgress =  mThriveViewModel.getValueProgress(valueName, dateFormat.format(today));
                        if(valueProgress == null){
                            valueProgress = new ValueProgress(valueName, calendar.getTime());
                            mThriveViewModel.insert(valueProgress);
                        }
                    }
                }
            });
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("last_time_started", today);
            editor.commit();
        }
    }



}