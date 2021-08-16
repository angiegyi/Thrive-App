package com.example.thrive;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewHabitActivity extends AppCompatActivity {

    // DB Model
    ThriveViewModel mThriveViewModel;

    //Habit Name
    TextInputEditText habitNameInput;

    // Related Values
    AutoCompleteTextView valuesInput;
    ArrayList<String> arrayList_values;
    ArrayAdapter<String> arrayListAdapter_values;

    // Target Frequency
    EditText everyInput;
    TextInputLayout everyMenuText;
    ArrayList<String> arrayEvery;
    AutoCompleteTextView everyMenuInput;
    EditText howOftenInput;

    // Reminder
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchReminder;
    // Time
    TimePicker timePicker;
    // Days
    Map<CheckBox, String> checkBoxObjects = new HashMap<>();
    String days_data = "";

    //Button
    Button createHabitButton;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_habit);

        // Habit Name
        habitNameInput = findViewById(R.id.habitNameInput);


        // Related Values
        // Values -> need to add values to database
        valuesInput = findViewById(R.id.relatedValuesInput);
        arrayList_values = new ArrayList<>();
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);
        // Pull values from DB and add to drop-down
        mThriveViewModel.getAllValues().observe(this, newData -> {
            for (Object obj : newData) {
                try {
                    arrayList_values.add(objectToJSONObject(obj).get("name").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        arrayListAdapter_values = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayList_values);
        valuesInput.setAdapter(arrayListAdapter_values);
        valuesInput.setThreshold(1);

        // Dummy Values
        addValues();


        // Target Frequency
        //      Every
        everyInput = findViewById(R.id.everyInput);
        everyMenuText = findViewById(R.id.everyMenuText);
        everyMenuInput = findViewById(R.id.everyMenuInput);
        setUpEveryMenu();

        //      How Often
        howOftenInput = findViewById(R.id.howOftenInput);

        // Reminder
        //      Switch
        switchReminder = findViewById(R.id.switchReminder);
        //      Time Picker
        timePicker = findViewById(R.id.timePickerHabit);
        timePicker.setIs24HourView(true);
        //      Days
        setUpCheckBoxes();

        //Button
        createHabitButton = findViewById(R.id.createHabitButton);
        createHabitButton.setOnClickListener(view -> onClickNewHabit());

    }

    protected void addValues(){
        // this is for testing purposes while we don't have any inserted values from the user
        Value testValue = new Value("fit3162", "education");
        mThriveViewModel.insert(testValue);
        Value testValue2 = new Value("fit3122", "education");
        mThriveViewModel.insert(testValue2);
    }

    protected void setUpEveryMenu(){
        arrayEvery = new ArrayList<>();
        arrayEvery.add("Day");
        arrayEvery.add("Week");
        arrayEvery.add("Month");
        everyMenuInput.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, arrayEvery));
        everyMenuInput.setThreshold(1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onClickNewHabit(){
        // Name
        String newName = habitNameInput.getText().toString();

        // Value
        String selectedValue = "";

        // Target Frequency
        Integer everyNum = Integer.parseInt(everyInput.getText().toString());
        String everyChoice = everyMenuInput.getText().toString();
        if (everyChoice.isEmpty()){
            everyChoice = "";
        }
        Integer howOftenNum = Integer.parseInt((howOftenInput.getText().toString()));

        //Reminder
        boolean isSwitch = switchReminder.isChecked();
        Integer timeHour = timePicker.getHour();
        Integer timeMinutes = timePicker.getMinute();

        // Days in days_data

        //Print in Logcat (Info)
        /*
        Log.i("HABIT", "Name   : " + newName);
        Log.i("HABIT", "Value  : " + selectedValue);
        Log.i("HABIT", "Target : " + everyNum + " " + everyChoice + "; " + howOftenNum + " times");
        Log.i("HABIT", "Switch : " + isSwitch);
        Log.i("HABIT", "Time   : " + timeHour + " : " + timeMinutes);
        Log.i("HABIT", "Days   : " + days_data);
        */

        addHabit(newName, selectedValue, everyNum, everyChoice, howOftenNum, isSwitch, timeHour, timeMinutes);
    }

    protected void addHabit(String name, String value, Integer freq, String period, Integer meas, boolean reminder, Integer remH, Integer remM){
        try {
            Integer days = 0;
            if (!days_data.equals("")) {
                days = Integer.parseInt(days_data);
            }
            String measurement = meas.toString();
            // Create a new Habit and HabitValue
            Habit newHabit = new Habit(name, period, freq, measurement, reminder, remH, remH, days);
            mThriveViewModel.insert(newHabit);
            HabitValue newHabitValue = new HabitValue();
            newHabitValue.setHabitName(name);
            newHabitValue.setValueName(value);
            //mThriveViewModel.insert(newHabitValue);
            Toast.makeText(getApplicationContext(),
                    "New Habit: " + name + " added." ,
                    Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.i("RESPONSE", e.toString());
        }
    }

    public static JSONObject objectToJSONObject(Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONObject obj = null;

        try {
            obj = new JSONObject(ow.writeValueAsString(object));
        } catch (JsonProcessingException | JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Sets up check boxes by adding on click listeners
     */
    public void setUpCheckBoxes(){
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbMon), "1");
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbTue), "2");
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbWed), "3");
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbThu), "4");
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbFri), "5");
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbSat), "6");
        checkBoxObjects.put((CheckBox) findViewById(R.id.cbSun), "7");

        for (CheckBox box : checkBoxObjects.keySet()){
            box.setOnClickListener(view -> getCheckBox(box));
        }
    }

    public void getCheckBox(CheckBox box){
        if (box.isChecked()) {
            days_data += checkBoxObjects.get(findViewById(box.getId()));
        }
    }



}
