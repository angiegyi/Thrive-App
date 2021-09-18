package com.example.thrive;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ProgressActivity extends AppCompatActivity {

    GraphView graph;
    TableLayout valuesTable;
    TextView title;
    TextView subtitle;
    static final int size = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        title = findViewById(R.id.progressTitle);
        title.setText("Your Values progress in the last "+ size +" days");
        subtitle = findViewById(R.id.progressSubTitle);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        Calendar calendar = Calendar.getInstance();
        String dateNow = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, -size+1);
        String dateStart = dateFormat.format(calendar.getTime());
        subtitle.setText("Date: " + dateStart + " - " + dateNow);

        graph = findViewById(R.id.progress_graph);
        initGraph();
        valuesTable = findViewById(R.id.table_values);
        initTable();
    }

    public LineGraphSeries<DataPoint> dummyData(){
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -size+1);
        DataPoint[] dataPoints = new DataPoint[size];
        for(int i = 0; i < size; i++){
            Date date = calendar.getTime();
            DataPoint dataPoint = new DataPoint(date, random.nextInt(15) + 1);
            dataPoints[i] = dataPoint;
            calendar.add(Calendar.DATE, 1);
        }
        return new LineGraphSeries<>(dataPoints);
    }

    @SuppressLint("SimpleDateFormat")
    public void initGraph(){

        LineGraphSeries<DataPoint> series = dummyData();
        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setTitle("Calendar");
        series.setColor(Color.RED);
        series.setBackgroundColor(Color.argb(25, 255, 0, 0));
        graph.addSeries(series);

        LineGraphSeries<DataPoint> series2 = dummyData();
        series2.setDrawBackground(true);
        series2.setDrawDataPoints(true);
        series2.setTitle("Calendar2");
        series2.setColor(Color.BLUE);
        series2.setBackgroundColor(Color.argb(25, 0, 0, 255));
        graph.addSeries(series2);



        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this, new SimpleDateFormat("MMM dd")));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setPadding(30);

        // set manual x bounds to have nice steps
        /*
        graph.getViewport().setMinX(d1.getTime());
        graph.getViewport().setMaxX(d5.getTime());
         */
        graph.getViewport().setXAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false, true);

    }

    public void initTable(){
        newValues("eat", 3, Color.RED);
        newValues("drink", 5, Color.BLUE);
    }

    public void newValues(String value, int total, int colour){
        // Create Table row
        TableRow tb = new TableRow(this);
        tb.setPadding(0,10,0,10);
        tb.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Value colour
        TableRow.LayoutParams paramColour = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                0.25f
        );
        String uri = "@android:drawable/checkbox_off_background";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        ImageView colourBox = new ImageView(this);
        colourBox.setImageDrawable(res);
        colourBox.setColorFilter(colour);
        colourBox.setLayoutParams(paramColour);
        colourBox.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tb.addView(colourBox);

        // Value text
        TableRow.LayoutParams paramValue = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                5.0f
        );
        TextView valueText = new TextView(this);
        valueText.setText(value);
        valueText.setLayoutParams(paramValue);
        valueText.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        valueText.setPadding(20,0,10,0);
        tb.addView(valueText);

        // Value Total
        TableRow.LayoutParams paramTotal = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                2.0f
        );
        TextView totalText = new TextView(this);
        totalText.setText(Integer.toString(total));
        totalText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        totalText.setLayoutParams(paramTotal);
        tb.addView(totalText);

        // Add to table layout
        valuesTable.addView(tb);
    }
}