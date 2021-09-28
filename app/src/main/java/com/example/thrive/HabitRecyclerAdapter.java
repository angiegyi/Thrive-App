package com.example.thrive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Habit;
import com.example.thrive.Database.entities.HabitValue;
import com.example.thrive.Database.entities.ValueProgress;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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

    public void resetData(){
        if(habitList != null) {
            habitList.clear();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView habitName;
        public TextView habitValue;
        public TextView habitPercentage;
        public TextView habitTime;
        public ProgressBar habitProgress;
        public Button habitButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habit_name);
            habitValue = itemView.findViewById(R.id.habit_value);
            habitPercentage = itemView.findViewById(R.id.habit_percent);
            habitTime = itemView.findViewById(R.id.habit_left);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull HabitRecyclerAdapter.ViewHolder holder, int position) {
        Habit habitObject = habitList.get(position);
        holder.habitName.setText(habitObject.getName());
        HabitValue habitValue = mThriveViewModel.getHabitValue(habitObject.getName());
        if(habitValue == null)
            holder.habitValue.setText("  ");
        else
            holder.habitValue.setText(habitValue.getValueName());
        float percentage = ((float) habitObject.getCounter() / (float) habitObject.getFrequency()) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        holder.habitPercentage.setText(decimalFormat.format(percentage) + "%");
        holder.habitProgress.setProgress(habitObject.getCounter());
        holder.habitProgress.setMax(habitObject.getFrequency());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        holder.habitButton.setOnClickListener(view -> {
            if (habitObject.getCounter() < habitObject.getFrequency()) {
                mThriveViewModel.updateHabitCounter(habitObject.getName(), habitObject.getCounter() + 1);
                ValueProgress valueProgress =  mThriveViewModel.getValueProgress(habitValue.getValueName(), dateFormat.format(today));
                //Log.i("HabitRec", String.valueOf(Objects.isNull(valueProgress)) + " " + habitValue.getValueName() + " " + dateFormat.format(today));
                if(Objects.isNull(valueProgress)){
                    valueProgress = new ValueProgress(habitValue.getValueName(),today);
                    mThriveViewModel.insert(valueProgress);
                }
                int curTotal = valueProgress.getTotal();
                mThriveViewModel.updateValueProgressTotal(habitValue.getValueName(), dateFormat.format(today), curTotal + 1);
                //Log.i("HabitRec", valueProgress.getProgressValue()+ " " + valueProgress.getDate() + " " + valueProgress.getTotal());
                //resetData();
            }
            else{
                Toast.makeText(habitContext, "You have completed: " + habitObject.getName() + " for this period",
                        Toast.LENGTH_LONG).show();
            }
        });
        int progress = habitObject.getFrequency() - habitObject.getCounter();
        String strHabitLeft =  progress + " times left per " + habitObject.getPeriod();
        holder.habitTime.setText(strHabitLeft);
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }
}
