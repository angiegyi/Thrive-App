package com.example.thrive.Database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {category.class, value.class, activity.class, activityCategory.class,
habit.class, habitValues.class, obstacle.class, obstacleImpactValue.class, checkIn.class}, version = 1, exportSchema = false)
public abstract class ThriveDatabase extends RoomDatabase {
    // database objects provides the interface of the underlying
    // sql database
    // returns a database instance
    public static final String CAR_DATABASE_NAME = "car_database";

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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ThriveDatabase.class, CAR_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
