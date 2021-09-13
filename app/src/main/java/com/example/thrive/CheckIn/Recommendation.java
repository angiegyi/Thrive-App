package com.example.thrive.CheckIn;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.Database.ThriveDAO;
import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.ActivityMood;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Recommendation {
    String relatedMood;
    private ThriveViewModel mThriveViewModel;
    List<Activity> activities;
    List<ActivityMood> activityMoods;
    ArrayList<ActivityScore> activityScores;



    public Recommendation(String relatedMood, ThriveViewModel viewModel) {
        this.mThriveViewModel = viewModel;
        this.relatedMood = relatedMood;
        this.activities = mThriveViewModel.findActivityByMoodName(relatedMood);
        this.activityMoods = mThriveViewModel.getMoodAndActivity(relatedMood);
        this.activityScores = new ArrayList<ActivityScore>();
    }

    public String getRelatedMood() {
        return relatedMood;
    }

    public void setRelatedMood(String relatedMood) {
        this.relatedMood = relatedMood;
    }

    public Activity getRecommendation(){
        for(int i=0; i < activities.size(); i++){
            int score = activities.get(i).getActivityRating() * activityMoods.get(i).getStrength();
            ActivityScore act = new ActivityScore();
            act.activity = activities.get(i);
            act.score = score;
            activityScores.add(act);
//            Log.i("test", "Activity: " + activityScores.get(i).activity.getActivityName()+
//                    " score: " +activityScores.get(i).score);

        }
        sortScores(activityScores);
//        Log.i("test", "SORTED: ");
//        for(int i=0; i < activities.size(); i++){
//            Log.i("test", "Activity: " + activityScores.get(i).activity.getActivityName()+
//                    " score: " +activityScores.get(i).score);
//
//        }
        int maximum = activityScores.get(0).score;
        int maxCount = 0;
        for(int i = 0;i < activityScores.size(); i++){
            ActivityScore act = activityScores.get(i);
            if(act.score == maximum){
                maxCount++;
            }
        }
        int randomNum = 0;
        if(maxCount > 1){
            randomNum = ThreadLocalRandom.current().nextInt(0, maxCount + 1);
        }
        Log.i("test", "getRecommendation: " + activityScores.get(randomNum).activity.getActivityName());
        return activityScores.get(randomNum).activity;
    }

    private ArrayList<ActivityScore> sortScores(ArrayList<ActivityScore> scores){
        for (int i = 0; i < scores.size(); i++) {
            for (int j = i + 1; j < scores.size(); j++) {
                ActivityScore tmp;
                if (scores.get(i).score < scores.get(j).score) {
                    tmp = scores.get(i);
                    scores.set(i, scores.get(j));
                    scores.set(j, tmp);
                }
            }
        }
        return scores;
    }

    class ActivityScore
    {
        public Activity activity;
        public int score;
    };
}
