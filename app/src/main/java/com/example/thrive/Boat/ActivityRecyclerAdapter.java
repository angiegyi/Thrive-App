package com.example.thrive.Boat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.ActivityInfo;
import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.ValueProgress;
import com.example.thrive.HabitRecyclerAdapter;
import com.example.thrive.HookBehaviours;
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
            //showLoadingPopUp(activityObject);
            Intent intent = new Intent(activityContext, ActivityInfo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name", activityObject.getActivityName());
            intent.putExtra("description", activityObject.getActivityDescription());
            activityContext.startActivity(intent);
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

    public void showLoadingPopUp(Activity activity){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activityContext.getApplicationContext());
        LayoutInflater inflater = (LayoutInflater)activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View checkInPopUpView = inflater.inflate(R.layout.recommendation_pop_up, null);
        dialogBuilder.setView(checkInPopUpView);
        AlertDialog checkInDialog = dialogBuilder.create();
        checkInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        checkInDialog.setCancelable(false); //prevent dialog box from getting dismissed by back button
        checkInDialog.setCanceledOnTouchOutside(false); // prevent dialog from getting dismissed on outside touch
        checkInDialog.show();
        Toast.makeText(activityContext.getApplicationContext(), "WTF", Toast.LENGTH_SHORT).show();

    }


}
