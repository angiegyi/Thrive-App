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
/*
* The Recommendation class
*
* To get a recommendation, you need to initialise a Recommendation object with the name of the mood
* and the view model so the object can access database methods.
* Then you simply call the getRecommendation() method.
*
* */
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

    private void updateRecentActivities(Activity activity){
        /*
        * This method updates the "recent_rank" attribute in the RECENTACTIVITY entity which
        * records all the activities that have been recommended by the algorithm. The recent_rank
        * attribute is used to determine the order of the most recent activities.
        * Rank 0 is the most recent activity that's been recommended.
        *
        * @params: Activity
        * @return: null
        */

        // inserting the new recent activity
        if (!mThriveViewModel.containsActivity(activity.getActivityName())){
            // if the activity has not been recommended before,
            // insert the activity into RECENTACTIVTY with rank 0
            RecentActivity act = new RecentActivity(activity.getActivityName());
            act.setRecentRank(0);
            mThriveViewModel.insert(act);
        } else {
            // if the activity has been recommended before, update its rank to 0.
            mThriveViewModel.updateRecentRank(activity.getActivityName(), 0);
        }
        // after insertion, increment other activities' rank by 1
        if(mThriveViewModel.recentActivityCount() >= 1) {
            // getting a list of "Less Recent" activities using
            // .getLessRecentActivities() from the view model
            List<RecentActivity> otherActivities = mThriveViewModel
                    .getLessRecentActivities(activity.getActivityName());
            for (int i = 0; i < otherActivities.size(); i++) {
                // for every other activity, get their rank and update by 1.
                RecentActivity act = otherActivities.get(i);
                mThriveViewModel.updateRecentRank(act.getActivityName(),act.getRecentRank()+1);
            }
        }
    }

    public Activity getRecommendation(){
        /*
        * This function recommends an Activity by sorting a list of activities against the
        *  activity's rating and the activity's strength on the given mood.
        *
        * @params:
        * @return: Activity
        * @complexity: best and worst = O(N^2) where N is the number of activities in the app.
        * */
        for(int i=0; i < activities.size(); i++){
            // calculate the activity's score. user rating * mood strength
            int score = activities.get(i).getActivityRating() * activityMoods.get(i).getStrength();
            ActivityScore act = new ActivityScore(); // create new Activity Score object
            act.activity = activities.get(i); // store the activity
            act.score = score; // store the score
            activityScores.add(act); // add the activityScore object to the list of scores
        }
        sortScores(activityScores);

        // Checking if there is more than one activity with the same score.
        int maximum = activityScores.get(0).score;
        int maxCount = 0;
        for(int i = 0;i < activityScores.size(); i++){
            ActivityScore act = activityScores.get(i);
            if(act.score == maximum){
                maxCount++;
            }
        }
        int randomNum = 0;
        if(maxCount > 1){ // if there is more than one activity with the same score,
            // choose a random activity out of the same scores
            randomNum = ThreadLocalRandom.current().nextInt(0, maxCount);
        }
        Activity recommended = activityScores.get(randomNum).activity; // get the chosen activity
        updateRecentActivities(recommended); // updated the recent activity ranks
        return recommended; // return the recommended activity
    }

    private ArrayList<ActivityScore> sortScores(ArrayList<ActivityScore> scores){
        /*
        * Sorts the ActivityScores from highest score to lowest score
        * @params: ArrayList<ActivityScore>
        * @return: null
        * @complexity: best and worst time: O(N^2) where N is the number of items in the array-list
        */
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
        /*
        * A struct to store the activity obj and its score
        * */
        public Activity activity;
        public int score;
    };
}
