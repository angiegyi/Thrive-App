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
import com.example.thrive.Database.entities.RecentActivity;
import com.example.thrive.Database.entities.Tool;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.ValueProgress;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Category.class, Value.class, Activity.class, Activity_category.class,
Habit.class, HabitValue.class, Obstacle.class, Obstacle_value.class, CheckIn.class, Mood.class,
        Hook.class, ActivityMood.class, ValueProgress.class, RecentActivity.class, Tool.class},
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
                                values2.put("mood_type", 0);
                                db.insert("mood", 1, values2);
                                //ThriveDatabase.databaseWriteExecutor.execute(() -> db.insert("mood", 0, values2));
                            }
                            ContentValues values3 = new ContentValues();
                            String[] positive_moods = {"happy", "calm", "energetic", "productive", "creative",
                            "adventurous", "relaxed", "confident"};
                            for(String mood: positive_moods){
                                values3.put("mood_name", mood);
                                values3.put("mood_type", 1);
                                db.insert("mood", 1, values3);
                            }

                            ContentValues values3a = new ContentValues();
                            String[] neutral_moods = {"apathetic", "bored", "nothing in particular"};
                            for(String mood: neutral_moods){
                                values3a.put("mood_name", mood);
                                values3a.put("mood_type", 2);
                                db.insert("mood", 1, values3a);
                            }

                            ContentValues values4 = new ContentValues();
                            String[] activities= {"Exercising", "Meditating", "Trying something new", "Connecting with people",	"Going for a stroll",
                                    "Doing personal hobbies or interests",	"Organising and tidying", "Disobeying on purpose", "Giving your mind a name and listening to it politely",
                            "Appreciating what your mind is trying to do", "Carrying it with you"};

                            String[] descriptions = {
                                    "Exercising brings many benefits. These benefits include:\n" +
                                    "Improved mood.\n" +
                                    "Reduced stress.\n" +
                                    "Improved self-esteem.\n" +
                                    "Improved body image.\n" +
                                    "Increased feelings of energy.\n" +
                                    "There are many types of physical activity, including swimming, running, jogging, walking, and dancing, to name a few.\n",

                            "Meditation brings many benefits, such as:\n" +
                                    "Gaining a new perspective on stressful situations.\n" +
                                    "Increasing self-awareness.\n" +
                                    "Focusing on the present.\n" +
                                    "Reducing negative emotions.\n" +
                                    "Increasing imagination and creativity.\n" +
                                    "Increasing patience and tolerance.\n" +
                                    "Meditation involves paying attention to your breath going in and out. " +
                                    "While noticing when the mind wanders from this task. This practice of directing your focus back onto " +
                                    "breathing builds the muscles of attention and mindfulness.\n",

                            "Here are a few important reasons to try new things regularly:\n" +
                                    "Meet new people.\n" +
                                    "Learn about yourself.\n" +
                                    "Expose yourself to new ideas.\n" +
                                    "Gives you more things to talk about.\n" +
                                    "Breaks up the routine of life.\n" +
                                    "Increases your overall satisfaction.\n" +
                                    "Alleviates boredom.\n" +
                                    "Grow as a person.\n",

                            "Here are a few important reasons to connect with others:\n" +
                                    "Mental Health. Socialization can help improve our mental and emotional health. \n" +
                                    "Confidence and self-esteem.\n" +
                                    "Increases quality of life.\n" +
                                    "Strengthens relationships.\n" +
                                    "Reduces risk of Alzheimer's\n" +
                                    "Boosts immunity and other physical health benefits.\n",

                            "There are numerous benefits of walking. Some of the most common include:\n" +
                                    "Improved sleep\n" +
                                    "Better endurance\n" +
                                    "Stress relief\n" +
                                    "Improvement in mood\n" +
                                    "Increased energy and stamina\n" +
                                    "Reduced tiredness that can increase mental alertness\n" +
                                    "Weight loss\n" +
                                    "Reduced cholesterol and improved cardiovascular (heart) health\n" +
                                    "Walking helps boost your mood because it increases blood flow and blood circulation to the brain and body. " +
                                    "When you exercise by walking, you calm your nerves, which can make you feel less stressed.\n",

                            "Hobbies bring a sense of fun and freedom to life that can help to minimize the impact of chronic stress. " +
                                    "Those who feel overwhelmed at a job, for example, can benefit from hobbies because they provide an " +
                                    "outlet for stress and something to look forward to after a hard day (or week) at a stressful job.",

                            "How does organizing help mental health?\n" +
                                    "Clutter and mess can create more stress and anxiety, but by cleaning, organizing, and reducing the clutter, " +
                                    "people are able to take control of their environment and create a more relaxing environment that helps them " +
                                    "focus better on the more pressing issues in their lives.",


                                    "Stand up and slowly walk around the room, reading this next sentence aloud several times. Here is the sentence: " +
                                            "“I cannot walk around this room.” Keep walking! Slowly but clearly repeat that sentence as you walk at least 5-6 times. " +
                                            "“I cannot walk around this room.” \n" +
                                            "\n" + "This exercise demonstrates that the mind’s power over you is an illusion and can very quickly " +
                                            "give you significantly more freedom to do hard things. You can easily build this into your life as a regular practice.\n",

                            "When we listen to another person, we choose whether we agree with what they have to say (or not). With our internal voice, " +
                                    "we don’t usually feel like we have that option to agree or disagree. Research has shown that naming your mind — " +
                                    "other than the one you call yourself — helps with this. Why? Because if your mind has a different name, " +
                                    "it is different from “you.”\n" +
                                    "\n" + "Pick any name you like. Now say hello to your mind by using its new name, as if you were being " +
                                    "introduced to it at a party. Of course, if you are around others while you’re reading this, do this in your head.\n",

                            "As you listen to your thoughts and notice when your mind starts to chatter, answer it back with something like, “Thanks for that thought, [insert mind’s name].” " +
                                    "If you speak to your mind dismissively, it will continue to problem-solve, so be sincere. You might want to add, “I really get that you’re trying to be of use," +
                                    " so thank you for that. But I’ve got this covered.” Say this out loud if you’re alone, or internally if you’re with others.\n" +
                                    "\n" +
                                    "Your mind will probably push back with thoughts like, “That’s silly — that won’t help!” Respond again with, “Thanks for that thought, [name]." +
                                    " Thank you — I really do see how you are trying to be of use.” You might consider inviting it to comment further by replying “Got anything else you have to say?”\n",

                            "Write down a recurring, critical thought on a small piece of paper. Maybe it’s “I’m stupid” or “I’m unloveable” or “I’m going to fail.” After you finish writing, hold up the paper " +
                                    "and look at it as if it were a precious and fragile page from an ancient manuscript. These words are an echo of your history.\n" +
                                    "\n" +
                                    "Even if the thought is painful, ask yourself if you’d be willing to honor that history by choosing to carry this piece of paper with you. " +
                                    "If you can get to “yes,” place it in your pocket, wallet or bag and let it come along for the ride. During the days you carry it, every so often " +
                                    "pat your wallet or bag (or wherever you keep it) to acknowledge that it is part of your journey, and it is welcome to come along.\n"};
                            ArrayList<int[]> listsOfStrengthsPositive = new ArrayList<int[]>();
                            ArrayList<int[]> listsOfStrengthsNegative = new ArrayList<int[]>();
                            ArrayList<int[]> listsOfStrengthsNeutral = new ArrayList<int[]>();
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
                            int[] apatheticStrength = {2,5,2,2,5,3,3,1,1,1,1};
                            int[] boredStrength = {5,2,4,4,4,4,3,1,1,1,1};
                            int[] nothingStrength = {5,2,4,4,4,4,3,1,1,1,1};
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
                            listsOfStrengthsNeutral.add(apatheticStrength);
                            listsOfStrengthsNeutral.add(boredStrength);
                            listsOfStrengthsNeutral.add(nothingStrength);
                            for(int i = 0; i < activities.length; i++){
                                values4.put("activity_name", activities[i]);
                                values4.put("activityDescription", descriptions[i]);
                                db.insert("activity", 1, values4);
                            }
                            ContentValues values4a = new ContentValues();
                            for(int i = 0; i < positive_moods.length; i++){
                                for(int j = 0; j < (activities.length); j++){
                                    values4a.put("mood_name", positive_moods[i]);
                                    values4a.put("activity_name", activities[j]);
                                    values4a.put("strength", listsOfStrengthsPositive.get(i)[j]);
                                    db.insert("activityMood", 1, values4a);
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

                            ContentValues values5a = new ContentValues();
                            for(int i = 0; i < neutral_moods.length; i++){
                                for(int j = 0; j < activities.length; j++){
                                    values5.put("mood_name", neutral_moods[i]);
                                    values5.put("activity_name", activities[j]);
                                    values5.put("strength", listsOfStrengthsNeutral.get(i)[j]);
                                    db.insert("activityMood", 1, values5);
                                }
                            }

                            String[] tools = {"Activity Timer", "App Blocker", "My Progress", "Activities"};
                            String[] icons = {"timer", "blocker", "progress", "activities"};
                            ContentValues values6 = new ContentValues();
                            for(int i = 0; i < tools.length; i++){
                                values6.put("tool_name", tools[i]);
                                values6.put("icon_name", icons[i]);
                                db.insert("tool", 1, values6);
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
