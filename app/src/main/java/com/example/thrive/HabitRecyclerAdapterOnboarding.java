package com.example.thrive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;

import java.util.ArrayList;

public class HabitRecyclerAdapterOnboarding extends RecyclerView.Adapter<HabitRecyclerAdapterOnboarding.viewHolder> {

    private ArrayList<Habit> habitList;
    private ThriveViewModel mThriveViewModel;
    private Context habitContext;

    public HabitRecyclerAdapterOnboarding(ThriveViewModel mThriveViewModel, Context context){
        this.mThriveViewModel = mThriveViewModel;
        this.habitContext = context;
    }

    public void setData(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        public TextView habitName;
        public TextView habitTime;
        public TextView habitValue;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habit_name_onboard);
            habitTime = itemView.findViewById(R.id.habit_time_onboard);
            habitValue = itemView.findViewById(R.id.habit_value_onboard);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_habit_onboard, parent, false);//CardView inflated as RecyclerView list item null;
        return new viewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Habit habitObject = habitList.get(position);
        holder.habitName.setText(habitObject.getName());
        String strHabitLeft = habitObject.getFrequency()+ " times left per " + habitObject.getPeriod();
        holder.habitTime.setText(strHabitLeft);
        HabitValue habitValue = mThriveViewModel.getHabitValue(habitObject.getName());
        if(habitValue == null)
            holder.habitValue.setText("  ");
        else
            holder.habitValue.setText(habitValue.getValueName());
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }
}

