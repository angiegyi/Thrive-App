package com.example.thrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.thrive.Database.ThriveViewModel;
import com.example.thrive.Database.entities.Value;
import com.example.thrive.Database.entities.ValueProgress;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ProgressActivity extends AppCompatActivity {

    GraphView graph;
    TableLayout valuesTable;
    TextView title;
    TextView subtitle;
    Random random;
    static final int size = 14; // Date range size
    ThriveViewModel mThriveViewModel;
    HashMap<String, int[]> colourMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        // Title
        title = findViewById(R.id.progressTitle);
        title.setText("Your Values progress in the last "+ size +" days");

        // SubTitle: Date Range
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
        Calendar calendar = Calendar.getInstance();
        String dateNow = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, -size+1);
        String dateStart = dateFormat.format(calendar.getTime());
        subtitle = findViewById(R.id.progressSubTitle);
        subtitle.setText("Date: " + dateStart + " - " + dateNow);

        // Database
        mThriveViewModel = new ViewModelProvider(this).get(ThriveViewModel.class);

        // Graph and Table
        graph = findViewById(R.id.progress_graph);
        valuesTable = findViewById(R.id.table_values);

        // Global Variables
        random = new Random();
        random.setSeed(123456789);
        colourMap = new HashMap<>();

        buildGraph();
    }

    public HashMap <String, HashMap<String, Integer>> getValueProgressData(){
        HashMap <String, HashMap<String, Integer>> dateValues = new HashMap<>();
        // Store all Value Progress data with date as key and a hashmap consist of Value name and total
        List<ValueProgress> valueProgresses = mThriveViewModel.getValueProgresses();
        for (ValueProgress obj : valueProgresses){
            //Log.i("GRAPH", "Get all values progress: " + obj.getProgressValue() +" " + obj.getDate() + " " + obj.getTotal());
            String date = obj.getDate();
            if(dateValues.containsKey(date)){
                dateValues.get(date).put(obj.getProgressValue(), obj.getTotal());
            }
            else {
                HashMap<String, Integer> newMap = new HashMap<>();
                newMap.put(obj.getProgressValue(), obj.getTotal());
                dateValues.put(date, newMap);
            }
        }
        return dateValues;
    }

    public HashMap <String, DataPoint[]> createDataPoints(){
        HashMap <String, HashMap<String, Integer>> dateValues = this.getValueProgressData();
        HashMap <String, DataPoint[]> valuesDataPoints = new HashMap<>();
        HashMap <String, Integer> valuesTotal = new HashMap<>();
        /*
        for(String key: dateValues.keySet()){
            Log.i("GRAPH", "DV: "+ key);
            HashMap<String, Integer> value = dateValues.get(key);
            for(String key2 : value.keySet()){
                Log.i("GRAPH", "    "+ key2 + " "+ value.get(key2));
            }
        }

         */
        // Create a hashmap with a value as key and a list of datapoints as value
        // Create a hashmap with a value as key and the total as value
        final int[] count = {0};
        List<Value> valueList = mThriveViewModel.getValues();
        for (Value obj: valueList){
            String valueName = obj.getName();
            valuesDataPoints.put(valueName, new DataPoint[size]);
            valuesTotal.put(valueName, 0);
            //Log.i("GRAPH", "Value: "+ valueName);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -size+1);

        // For each date in range, check if the date exists in data
        // If yes, check in the list of values if there is that value in the data
        // If yes, set the date as x value for the value dataPoints and total as y value
        // Else, set 0 as y value
        // int counter = 0;
        for(int i = 0; i < size; i++){
            Date date = calendar.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = dateFormat.format(date);
            //Log.i("GRAPH", "Date "+ dateStr);
            if(dateValues.containsKey(dateStr)){
                HashMap<String, Integer> valuePoints = dateValues.get(dateStr);
                for (String valueName : valuesDataPoints.keySet()) {
                    int yValue = 0;
                    if(valuePoints != null && valuePoints.containsKey(valueName)){
                        yValue = valuePoints.get(valueName);
                        //Log.i("GRAPH", "get total "+ counter);
                        // counter +=1;
                    }
                    DataPoint dataPoint = new DataPoint(date, yValue);
                    valuesDataPoints.get(valueName)[i] = dataPoint;
                    valuesTotal.put(valueName, valuesTotal.get(valueName) + yValue);
                    //Log.i("GRAPH"," 1 " + i + " " + valueName +" " + valuesTotal.get(valueName));
                }
            }
            else{
                for (String valueName : valuesDataPoints.keySet()) {
                    Integer yValue = 0;
                    DataPoint dataPoint = new DataPoint(date, yValue);
                    valuesDataPoints.get(valueName)[i] = dataPoint;
                    valuesTotal.put(valueName, valuesTotal.get(valueName) + yValue);
                    ///Log.i("GRAPH", " 2 " + i + " " + valueName +" " + valuesTotal.get(valueName));
                }
            }
            calendar.add(Calendar.DATE, 1);
        }
        for (String valueName : valuesTotal.keySet()) {
            int[] rgb = randomColourGenerator(valueName);
            this.newValues(valueName,
                    valuesTotal.get(valueName),
                    Color.rgb(rgb[0], rgb[1], rgb[2]));
        }
        return valuesDataPoints;
    }

    public int[] randomColourGenerator(String key){
        int[] rgb = new int[3];
        // Range from 50 to 150
        rgb[0] = random.nextInt(180) + 50; // red
        rgb[1] = random.nextInt(180) + 50; // green
        rgb[2] =  random.nextInt(180) + 50;; // blue
        colourMap.put(key, rgb);
        return rgb;
    }


    public void buildGraph(){
        HashMap <String, DataPoint[]> valuesDataPoints = this.createDataPoints();
        for (String valueName : valuesDataPoints.keySet()) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(valuesDataPoints.get(valueName));
            series.setDrawBackground(true);
            series.setDrawDataPoints(true);
            series.setTitle(valueName);
            int[] rgb = colourMap.get(valueName);
            series.setColor(Color.rgb(rgb[0], rgb[1], rgb[2]));
            series.setBackgroundColor(Color.argb(25, rgb[0], rgb[1], rgb[2]));
            graph.addSeries(series);
        }

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this, new SimpleDateFormat("MMM dd")));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setPadding(30);

        // set manual x bounds to have nice steps
        graph.getViewport().setXAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false, true);
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

    public LineGraphSeries<DataPoint> dummyData(){
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
    public void dummyGraph(){

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

        newValues("eat", 3, Color.RED);
        newValues("drink", 5, Color.BLUE);

    }

}