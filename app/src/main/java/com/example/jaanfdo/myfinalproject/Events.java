package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Events;
import com.example.jaanfdo.myfinalproject.Database.EventsDB;

import java.util.ArrayList;

public class Events extends AppCompatActivity {
    EventsDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        database = new EventsDB(this);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayList detail = getListData();
        final ListView lv = (ListView) findViewById(R.id.eventlistview);
        lv.setAdapter(new CustomListAdapter_Events(this, detail));
    }

    private ArrayList getListData() {
        ArrayList<EventsBL> results = new ArrayList<EventsBL>();
        Cursor cursor = database.DisplayAll();

        if (cursor.moveToFirst()) {
            do {
                EventsBL newsData = new EventsBL();
                newsData.setId(cursor.getString(0));
                newsData.setEventname(cursor.getString(1));
                newsData.setCourse(cursor.getString(2));
                newsData.setDate(cursor.getString(3));
                newsData.setTime(cursor.getString(4));
                newsData.setPlace(cursor.getString(5));

                results.add(newsData);

            } while (cursor.moveToNext());
        }

        return results;
    }

    public void addEvents(View view){
        Intent i = new Intent(this, AddEvents.class);
        startActivity(i);
    }

}
