package com.example.thrive.Database;
import android.content.ContentValues;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.Activity_category;
import com.example.thrive.Database.entities.Category;
import com.example.thrive.Database.entities.CheckIn;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.Mood;
import com.example.thrive.Database.entities.Obstacle;
import com.example.thrive.Database.entities.Obstacle_value;
import com.example.thrive.Database.entities.Value;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Category.class, Value.class, Activity.class, Activity_category.class,
Habit.class, HabitValue.class, Obstacle.class, Obstacle_value.class, CheckIn.class, Mood.class}, version = 1, exportSchema = false)
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

                            String[] moods = {"stressed", "nervous", "restless", "irritable",
                            "on edge", "unsettled", "creative", "adventurous", "happy", "angry", "sad",
                            "calm", "comfortable", "content", "productive", "lazy", "tired", "stressed",
                            "energized"};

//                            ContentValues values2 = new ContentValues();
//                            for(String mood: moods){
//                                values2.put("mood_name", mood);
//                                values2.put("mood_isPositive", true);
//                                db.insert("mood", 1, values2);
//                            }
                        }
                        public void onOpen (SupportSQLiteDatabase db) {
                            // do something every time database is open
                        }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ThriveDatabase.class, THRIVE_DATABASE_NAME).addCallback(rdc)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
