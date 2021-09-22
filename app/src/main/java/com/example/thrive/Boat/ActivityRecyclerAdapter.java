package com.example.thrive.Boat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.ValueProgress;
import com.example.thrive.HabitRecyclerAdapter;
import com.example.thrive.R;
import com.google.android.material.slider.Slider;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<ActivityRecyclerAdapter.ActivityViewHolder> {

    private ArrayList<Activity> activityList;
    private ThriveViewModel mThriveViewModel;
    private Context activityContext;

    public ActivityRecyclerAdapter(ThriveViewModel mThriveViewModel, Context context){
        this.mThriveViewModel = mThriveViewModel;
        this.activityContext = context;
    }

    public void setData(ArrayList<Activity> activityList) {
        this.activityList = activityList;
    }

    public void resetData(){
        if(activityList != null) {
            activityList.clear();
        }
    }

    @NonNull
    public ActivityRecyclerAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_activity, parent, false);//CardView inflated as RecyclerView list item null;
        return new ActivityRecyclerAdapter.ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityRecyclerAdapter.ActivityViewHolder holder, int position) {
        Activity activityObject = activityList.get(position);
        holder.activityName.setText(activityObject.getActivityName());
        holder.seeMoreButton.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }


    public static class ActivityViewHolder extends RecyclerView.ViewHolder{

        public TextView activityName;
        public TextView activityRelatedValues;
        public Slider activitySlider;
        public Button seeMoreButton;

        public ActivityViewHolder(@NonNull View view) {
            super(view);
            activityName = view.findViewById(R.id.card_activity_name);
            activityRelatedValues = view.findViewById(R.id.activity_related_text);
            activitySlider = view.findViewById(R.id.card_activity_rating_slider);
            seeMoreButton = view.findViewById(R.id.card_activity_see_more_btn);
        }
    }



}
