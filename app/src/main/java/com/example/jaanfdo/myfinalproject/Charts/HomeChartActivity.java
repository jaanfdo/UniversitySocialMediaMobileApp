package com.example.jaanfdo.myfinalproject.Charts;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jaanfdo.myfinalproject.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class HomeChartActivity extends AppCompatActivity {
    LineChart linechart;
    BarChart barchart;
    PieChart piechart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chart);

        linechart = (LineChart) findViewById(R.id.linechart);
        barchart = (BarChart) findViewById(R.id.barchart);
        piechart = (PieChart) findViewById(R.id.piechart);

        LineChart();
        BarChart();
        PieChart();
    }


    private void LineChart(){
        linechart.setDrawGridBackground(false);
        linechart.getDescription().setEnabled(false);
        linechart.setTouchEnabled(true);
        linechart.setDragEnabled(true);
        linechart.setScaleEnabled(true);
        linechart.setPinchZoom(true);
        linechart.setBackgroundColor(Color.WHITE);
        linechart.getAxisRight().setEnabled(false);

        LimitLine llXAxis = new LimitLine(0f, "Index 0");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        llXAxis.setTextSize(10f);

        LimitLine ll1 = new LimitLine(150f, "Upper Limit");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        //ll1.setTypeface(tf);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        XAxis xAxis = linechart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = linechart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(llXAxis);
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        Legend l = linechart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setFormSize(10f);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);

        float[] dataObjects = {100,150,200,450,100,300,250};
        float[] dataObjects1 = {120,350,100,120,240,260,320};
        float[] dataObjects2 = {70,450,400,520,300,360,300};
        List<Entry> entries = new ArrayList<Entry>();
        List<Entry> entries1 = new ArrayList<Entry>();
        List<Entry> entries2 = new ArrayList<Entry>();

        for (int i = 0; i < 7; i++) {
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
        linechart.setData(lineData);
        linechart.invalidate(); // refresh
    }

    private void BarChart(){
        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.getDescription().setEnabled(false);
        barchart.setMaxVisibleValueCount(60);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(false);
        barchart.getAxisRight().setEnabled(false);

        XAxis xAxis1 = barchart.getXAxis();
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setDrawAxisLine(true);
        xAxis1.setDrawGridLines(false);

        YAxis leftAxis1 = barchart.getAxisLeft();
        leftAxis1.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis1.setDrawAxisLine(true);
        leftAxis1.setDrawGridLines(false);


        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(1, 90f));
        yVals1.add(new BarEntry(2, 40f));
        yVals1.add(new BarEntry(3, 55f));
        yVals1.add(new BarEntry(4, 30f));
        yVals1.add(new BarEntry(5, 80f));

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");

        //BarData data = new BarData(labels, yVals1);
        //barchart.setData(data);

        BarDataSet set1;
        if (barchart.getData() != null && barchart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barchart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barchart.getData().notifyDataChanged();
            barchart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1,  "The year 2017");
            set1.setDrawIcons(false);
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            barchart.setData(data);
        }
        barchart.invalidate(); // refresh
    }

    private void PieChart(){
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(5, 10, 5, 5);
        piechart.setDragDecelerationFrictionCoef(0.95f);
        piechart.setTransparentCircleRadius(61f);
        piechart.setHighlightPerTapEnabled(true);
        piechart.animateY(1400, Easing.EasingOption.EaseInQuad);

        Legend l = piechart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        piechart.setEntryLabelColor(Color.WHITE);
        piechart.setEntryLabelTextSize(12f);

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(4f,"Jan"));
        entries.add(new PieEntry(7f,"Feb"));
        entries.add(new PieEntry(9f,"Mar"));
        entries.add(new PieEntry(3f,"Apr"));
        entries.add(new PieEntry(6f,"May"));

        PieDataSet dataSet = new PieDataSet(entries, "The year 2017 Born Percentage");

        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        piechart.setData(data);

        piechart.highlightValues(null);
        piechart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.obarchart:
                Intent intent1 = new Intent(this,BarChartActivity.class);
                startActivity(intent1);
                break;
            case R.id.olinechart:
                Intent intent2 = new Intent(this,LineChartActivity.class);
                startActivity(intent2);
            break;
            case R.id.opiechart:
                Intent intent3 = new Intent(this, PieChartActivity.class);
                startActivity(intent3);
            break;
        }
         return true;
    }
}
