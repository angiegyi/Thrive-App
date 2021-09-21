package com.example.thrive;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Obstacle;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ObstacleRecyclerAdapter extends RecyclerView.Adapter<ObstacleRecyclerAdapter.ViewHolder> {

    private ArrayList<Obstacle> obstacleList;
    private Context obstacleContext;

    public ObstacleRecyclerAdapter(Context context){
        this.obstacleContext = context;
    }

    public void setData(ArrayList<Obstacle> obstacleList) {
        this.obstacleList = obstacleList;
    }

    public void resetData(){
        if(obstacleList != null) {
            obstacleList.clear();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView obstacleName;
        public TextView obstacleDescription;
        public TextView obstacle_related_text;
        public Button new_hook_button;
        TextInputLayout valuesTextInputLayout;
        AutoCompleteTextView valuesInput;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            obstacleName = itemView.findViewById(R.id.obstacle_name);
            obstacleDescription = itemView.findViewById(R.id.obstacle_description);
            obstacle_related_text = itemView.findViewById(R.id.obstacle_related_text);
            new_hook_button = itemView.findViewById(R.id.new_hook_button);
        }
    }

    @NonNull
    @Override
    public ObstacleRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_obstacle, parent, false);//CardView inflated as RecyclerView list item null;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObstacleRecyclerAdapter.ViewHolder holder, int position) {
        Obstacle obstacleObject = obstacleList.get(position);
        holder.obstacleName.setText(obstacleObject.getObstacleName());
        holder.obstacleDescription.setText(obstacleObject.getDescription());
        // need to get the value
        System.out.println("hello " + obstacleObject.getValueName());
        holder.obstacle_related_text.setText("hello");
        holder.new_hook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String obstacleName = obstacleObject.getObstacleName();
                Intent intent = new Intent(obstacleContext,HookBehaviours.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", obstacleName);
                obstacleContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return obstacleList.size();
    }
}
