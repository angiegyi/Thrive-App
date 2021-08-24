package com.example.thrive;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.thrive.Database.entities.Mood;

import java.util.List;

public class ThriveArrayAdapter extends ArrayAdapter<Mood> {
    List<Mood> mAllPositiveMoods;
    public ThriveArrayAdapter(@NonNull Context context, int resource, @NonNull List<Mood> objects) {
        super(context, resource, objects);
    }
    public void setPositiveMoods(List<Mood> newData) {
        mAllPositiveMoods=newData;
    }

    @Override
    public int getCount() {
        if (mAllPositiveMoods == null)
            return 0;
        else
            return mAllPositiveMoods.size();
    }

    public List<Mood> getmAllPositiveMoods(){return mAllPositiveMoods;}
}
