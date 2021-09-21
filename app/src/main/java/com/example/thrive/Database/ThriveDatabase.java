package com.example.thrive.Database;
import android.content.ContentValues;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.ActivityMood;
import com.example.thrive.Database.entities.Activity_category;
import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.CheckIn;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.Hook;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.ValueProgress;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Category.class, Value.class, Activity.class, Activity_category.class,
Habit.class, HabitValue.class, Obstacle.class, Obstacle_value.class, CheckIn.class, Mood.class,
        Hook.class, ActivityMood.class, ValueProgress.class},
        version = 1, exportSchema = false)
public abstract class ThriveDatabase extends RoomDatabase {
    // database objects provides the interface of the underlying
    // sql database
    // returns a database instance
    public static final String THRIVE_DATABASE_NAME = "thrive_database";

    public abstract ThriveDAO ThriveDAO();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile ThriveDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ThriveDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ThriveDatabase.class) {
                if (INSTANCE == null) {
                    RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
                        public void onCreate (SupportSQLiteDatabase db) {
                            // do something after database has been created
                            ContentValues values = new ContentValues();
                            String[] categories = {"people", "education", "mindfulness", "self-help",
                            "physical health", "mental health", "financial", "relationships", "family",
                            "recreational", "career", "creativity", "networking", "pets", "other"};
                            for(String cat: categories){
                                values.put("category_name", cat);
                                db.insert("category",1, values);
                            }

                            ContentValues values2 = new ContentValues();
                            String[] negative_moods = {"sad", "anxious", "depressed", "obsessive thoughts", "tired",
                            "stressed", "burnt-out", "low self-esteem"};
                            for(String mood: negative_moods){
                                values2.put("mood_name", mood);
                                values2.put("mood_isPositive", false);
                                db.insert("mood", 1, values2);
                                //ThriveDatabase.databaseWriteExecutor.execute(() -> db.insert("mood", 0, values2));
                            }
                            ContentValues values3 = new ContentValues();
                            String[] positive_moods = {"happy", "calm", "energetic", "productive", "creative",
                            "adventurous", "relaxed", "confident"};
                            for(String mood: positive_moods){
                                values3.put("mood_name", mood);
                                values3.put("mood_isPositive", true);
                                db.insert("mood", 1, values3);
                            }

                            ContentValues values4 = new ContentValues();
                            String[] activities= {"exercising", "meditation", "trying something new", "connecting with people",	"go for a walk",
                                    "personal hobbies/interests",	"organising/tidying", "disobey on purpose", "give your mind a name and listen to it politely",
                            "appreciate what your mind is trying to do", "carry it with you "};
                            ArrayList<int[]> listsOfStrengthsPositive = new ArrayList<int[]>();
                            ArrayList<int[]> listsOfStrengthsNegative = new ArrayList<int[]>();
                            int[] happyStrength = {5,5,4,5,5,5,3,1,1,1,1};
                            int[] calmStrength = {2,5,2,2,5,3,3,1,1,1,1};
                            int[] energeticStrength = {5,2,4,4,4,4,3,1,1,1,1};
                            int[] productiveStrength = {4,4,5,3,3,4,5,1,1,1,1};
                            int[] creativeStrength = {1,2,5,1,1,5,1,1,1,1,1};
                            int[] adventurousStrength = {3,1,5,5,5,3,1,1,1,1,1};
                            int[] relaxedStrength = {1,5,1,3,3,5,2,1,1,1,1};
                            int[] confidentStrength = {5,3,5,4,3,4,3,1,1,1,1};
                            int[] sadStrength = {3,5,3,3,4,5,3,5,5,5,5};
                            int[] anxiousStrength = {5,5,5,5,4,3,3,5,5,5,5};
                            int[] depressedStrength = {5,5,4,5,5,5,5,5,5,5,5};
                            int[] obsessiveThoughtsStrength = {5,5,3,3,5,3,5,5,5,5,5};
                            int[] tiredStrength = {5,5,3,3,3,3,3,5,5,5,5};
                            int[] stressedStrength = {4,5,2,3,5,5,5,5,5,5,5};
                            int[] burntOutStrength = {5,5,3,4,5,5,3,5,5,5,5};
                            int[] lowSelfEsteemStrength= {5,3,5,3,3,3,3,5,5,5,5};
                            listsOfStrengthsPositive.add(happyStrength);
                            listsOfStrengthsPositive.add(calmStrength);
                            listsOfStrengthsPositive.add(energeticStrength);
                            listsOfStrengthsPositive.add(productiveStrength);
                            listsOfStrengthsPositive.add(creativeStrength);
                            listsOfStrengthsPositive.add(adventurousStrength);
                            listsOfStrengthsPositive.add(relaxedStrength);
                            listsOfStrengthsPositive.add(confidentStrength);
                            listsOfStrengthsNegative.add(sadStrength);
                            listsOfStrengthsNegative.add(anxiousStrength);
                            listsOfStrengthsNegative.add(depressedStrength);
                            listsOfStrengthsNegative.add(obsessiveThoughtsStrength);
                            listsOfStrengthsNegative.add(tiredStrength);
                            listsOfStrengthsNegative.add(stressedStrength);
                            listsOfStrengthsNegative.add(burntOutStrength);
                            listsOfStrengthsNegative.add(lowSelfEsteemStrength);
                            for(String activity: activities){
                                values4.put("activity_name", activity);
                                db.insert("activity", 1, values4);
                            }
                            for(int i = 0; i < positive_moods.length; i++){
                                for(int j = 0; j < (activities.length); j++){
                                    values4.put("mood_name", positive_moods[i]);
                                    values4.put("activity_name", activities[j]);
                                    values4.put("strength", listsOfStrengthsPositive.get(i)[j]);
                                    db.insert("activityMood", 1, values4);
                                }
                            }
                            ContentValues values5 = new ContentValues();
                            for(int i = 0; i < negative_moods.length; i++){
                                for(int j = 0; j < activities.length; j++){
                                    values5.put("mood_name", negative_moods[i]);
                                    values5.put("activity_name", activities[j]);
                                    values5.put("strength", listsOfStrengthsNegative.get(i)[j]);
                                    db.insert("activityMood", 1, values5);
                                }
                            }

                        }
                        public void onOpen (SupportSQLiteDatabase db) {
                            // do something every time database is open
                        }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ThriveDatabase.class, THRIVE_DATABASE_NAME).addCallback(rdc).allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
