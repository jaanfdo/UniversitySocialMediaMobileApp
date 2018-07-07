package com.example.jaanfdo.myfinalproject.Charts;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jaanfdo.myfinalproject.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class HomeChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chart);

        // in this example, a LineChart is initialized from xml
        LineChart chart = (LineChart) findViewById(R.id.chart);

        float[] dataObjects = {100,150,200,450,100,300,250};
        float[] dataObjects1 = {120,350,100,120,240,260,320};
        float[] dataObjects2 = {70,450,400,520,300,360,300};
        List<Entry> entries = new ArrayList<Entry>();
        List<Entry> entries1 = new ArrayList<Entry>();
        List<Entry> entries2 = new ArrayList<Entry>();

        for (int i = 0; i < 7; i++) {
            // turn your data into Entry objects
            entries.add(new Entry(i, dataObjects[i % dataObjects.length]));
            entries1.add(new Entry(i, dataObjects1[i % dataObjects1.length]));
            entries2.add(new Entry(i, dataObjects2[i % dataObjects2.length]));
        }
        LineDataSet setComp1 = new LineDataSet(entries, "Company 1");
        LineDataSet setComp2 = new LineDataSet(entries1, "Company 2");
        LineDataSet setComp3 = new LineDataSet(entries2, "Company 3");

        setComp1.setColor(Color.BLACK);
        setComp1.setValueTextColor(Color.BLACK); // styling, ...
        setComp2.setColor(Color.BLUE);
        setComp2.setValueTextColor(Color.BLUE); // styling, ...
        setComp3.setColor(Color.GREEN);
        setComp3.setValueTextColor(Color.GREEN); // styling, ...

        List<ILineDataSet> dataSet = new ArrayList<ILineDataSet>(); // add entries to dataset
        dataSet.add(setComp1);
        dataSet.add(setComp2);
        dataSet.add(setComp3);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }



    public void  piechartclick(View view){
        Intent intent1 = new Intent(this, PieChartActivity.class);
        startActivity(intent1);
    }

    public void  barchartclick(View view){
        Intent intent1 = new Intent(this,BarChartActivity.class);
        startActivity(intent1);
    }

    public void  linechartclick(View view){
        Intent intent1 = new Intent(this,LineChartActivity.class);
        startActivity(intent1);
    }
}
