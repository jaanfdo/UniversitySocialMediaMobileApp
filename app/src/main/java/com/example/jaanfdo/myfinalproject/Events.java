package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;

import java.util.ArrayList;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayList image_details = getListData();
        final ListView lv = (ListView) findViewById(R.id.eventlistview);
        lv.setAdapter(new CustomListAdapter(this, image_details));
    }

    private ArrayList getListData() {
        ArrayList<EventsBL> results = new ArrayList<EventsBL>();
        EventsBL newsData = new EventsBL();
        newsData.setEventname("Dance of Democracy");
        newsData.setCourse("Bsc (Hon) in Softwar Eniginering");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        EventsBL newsData1 = new EventsBL();
        newsData1.setEventname("Dance");
        newsData1.setCourse("Bsc (Hon) in Eniginering");
        newsData1.setDate("May 26, 2013, 13:35");
        results.add(newsData1);

        return results;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void addEvents(View view){
        Intent i = new Intent(this, AddEvents.class);
        startActivity(i);
    }

}
