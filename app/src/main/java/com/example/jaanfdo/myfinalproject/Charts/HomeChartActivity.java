package com.example.jaanfdo.myfinalproject.Charts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jaanfdo.myfinalproject.R;

public class HomeChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chart);
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
