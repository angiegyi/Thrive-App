package com.example.thrive;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityInfo extends AppCompatActivity {
    View view;
    TextView name;
    TextView des;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_activity_info);
        Bundle extras = getIntent().getExtras();
        view = findViewById(R.id.activityInfoCardview);
        view.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        name = view.findViewById(R.id.tv_activity);
        name.setText(extras.getString("name"));
        des = view.findViewById(R.id.tv_description);
        des.setText(extras.getString("description"));
        back = view.findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
