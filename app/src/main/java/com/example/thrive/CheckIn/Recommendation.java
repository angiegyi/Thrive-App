package com.example.thrive.CheckIn;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.example.thrive.ActivityInfo;
import com.example.thrive.Database.ThriveDAO;
import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.ActivityMood;
import com.example.thrive.Database.entities.RecentActivity;

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

    public void updateRecentActivities(Activity activity){
        // insert new recent activity
        Log.i("test", "updateRecentActivities: PLEASE WORK PLEASE " + mThriveViewModel.containsActivity(activity.getActivityName()));
        if (!mThriveViewModel.containsActivity(activity.getActivityName())){
            RecentActivity act = new RecentActivity(activity.getActivityName());
            act.setRecentRank(0);
            Log.i("test", "here: ");
            mThriveViewModel.insert(act);
        } else {
            // update occurred activity rank
            mThriveViewModel.updateRecentRank(activity.getActivityName(), 0);
        }
        // increment other activities' rank by 1
        if(mThriveViewModel.recentActivityCount() >= 1) {
            List<RecentActivity> otherActivities = mThriveViewModel.getLessRecentActivities(activity.getActivityName());
            for (int i = 0; i < otherActivities.size(); i++) {
                RecentActivity act = otherActivities.get(i);
                mThriveViewModel.updateRecentRank(act.getActivityName(), act.getRecentRank() + 1);
            }
        }
    }

    public Activity getRecommendation(){
        for(int i=0; i < activities.size(); i++){
            int score = activities.get(i).getActivityRating() * activityMoods.get(i).getStrength();
            ActivityScore act = new ActivityScore();
            act.activity = activities.get(i);
            act.score = score;
            activityScores.add(act);

        }
        sortScores(activityScores);

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
            randomNum = ThreadLocalRandom.current().nextInt(0, maxCount);
        }
        Activity recommended = activityScores.get(randomNum).activity;
        updateRecentActivities(recommended);
        return recommended;
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
