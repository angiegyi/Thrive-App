package com.example.thrive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;

import java.util.ArrayList;

public class HabitRecyclerAdapter extends RecyclerView.Adapter<HabitRecyclerAdapter.ViewHolder> {

    private ArrayList<Habit> habitList;
    private ThriveViewModel mThriveViewModel;
    private Context habitContext;

    public HabitRecyclerAdapter(ThriveViewModel mThriveViewModel, Context context){
        this.mThriveViewModel = mThriveViewModel;
        this.habitContext = context;
    }

    public void setData(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView habitName;
        public TextView habitLeft;
        public ProgressBar habitProgress;
        public Button habitButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habit_name);
            habitLeft = itemView.findViewById(R.id.habit_left);
            habitProgress = itemView.findViewById(R.id.habit_progress_bar);
            habitButton = itemView.findViewById(R.id.habit_button);
        }
    }


    @NonNull
    @Override
    public HabitRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_habit, parent, false);//CardView inflated as RecyclerView list item null;
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull HabitRecyclerAdapter.ViewHolder holder, int position) {
        Habit habitObject = habitList.get(position);
        holder.habitName.setText(habitObject.getName());
        holder.habitProgress.setProgress(habitObject.getCounter());
        holder.habitProgress.setMax(habitObject.getFrequency());
        holder.habitButton.setOnClickListener(view -> {
            if (habitObject.getCounter() < habitObject.getFrequency()) {
                mThriveViewModel.updateHabitCounter(habitObject.getName(), habitObject.getCounter() + 1);
                habitList.clear();
            }
            else{
                Toast.makeText(habitContext, "You have completed: " + habitObject.getName() + " for this period",
                        Toast.LENGTH_LONG).show();
            }
        });
        String addS = "";
        if(Integer.parseInt(habitObject.getMeasurement()) > 1)
            addS = "s";
        String strHabitLeft = habitObject.getCounter() + "/" + habitObject.getFrequency()+ " times left per " + habitObject.getMeasurement() + " " + habitObject.getPeriod()+addS;
        holder.habitLeft.setText(strHabitLeft);
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }
}
