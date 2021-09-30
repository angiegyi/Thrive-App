package com.example.thrive.Boat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.ActivityInfo;
import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Activity;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.Tool;
import com.example.thrive.Database.entities.ValueProgress;
import com.example.thrive.HabitRecyclerAdapter;
import com.example.thrive.HookBehaviours;
import com.example.thrive.ProgressActivity;
import com.example.thrive.R;
import com.google.android.material.slider.Slider;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BoatRecyclerAdapter extends RecyclerView.Adapter<BoatRecyclerAdapter.BoatViewHolder> {

    private ArrayList<Tool> toolList;
    private ThriveViewModel mThriveViewModel;
    private Context boatContext;
    private String icon;

    public BoatRecyclerAdapter(ThriveViewModel mThriveViewModel, Context context){
        this.mThriveViewModel = mThriveViewModel;
        this.boatContext = context;
    }

    public void setData(ArrayList<Tool> toolList) {
        this.toolList = toolList;
    }

    public void resetData(){
        if(toolList != null) {
            toolList.clear();
        }
    }

    @NonNull
    public BoatRecyclerAdapter.BoatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tools, parent, false);//CardView inflated as RecyclerView list item null;
        return new BoatRecyclerAdapter.BoatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoatRecyclerAdapter.BoatViewHolder holder, int position) {
        Tool tool = toolList.get(position);
        holder.toolName.setText(tool.getToolName());

        String name = tool.getIcon_name();
        int id = boatContext.getResources().getIdentifier(name, "drawable", boatContext.getPackageName());
        Drawable drawable = boatContext.getResources().getDrawable(id);
        holder.icon.setImageDrawable(drawable);
        holder.arrow.setOnClickListener(view -> {
            if(tool.getToolName().equals("My Progress")){
                Intent intent = new Intent(boatContext, ProgressActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                boatContext.startActivity(intent);
            } else if (tool.getToolName().equals("Activities")){
                Intent intent = new Intent(boatContext, ActivitiesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                boatContext.startActivity(intent);
            }
        });

        holder.cl.setOnClickListener(view->{
            if(tool.getToolName().equals("My Progress")){
                Intent intent = new Intent(boatContext, ProgressActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                boatContext.startActivity(intent);
            } else if (tool.getToolName().equals("Activities")){
                Intent intent = new Intent(boatContext, ActivitiesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                boatContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return toolList.size();
    }


    public static class BoatViewHolder extends RecyclerView.ViewHolder{

        public TextView toolName;
        public ImageView icon;
        public Button arrow;
        public ConstraintLayout cl;

        public BoatViewHolder(@NonNull View view) {
            super(view);
            toolName = view.findViewById(R.id.tool_name);
            icon = view.findViewById(R.id.icon);
            arrow = view.findViewById(R.id.arrow);
            cl = view.findViewById(R.id.tool_constraintLayout);
        }
    }


}
