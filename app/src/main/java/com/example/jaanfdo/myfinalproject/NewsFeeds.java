package com.example.jaanfdo.myfinalproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Events;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_News;
import com.example.jaanfdo.myfinalproject.Database.EventsDB;
import com.example.jaanfdo.myfinalproject.Database.NewsDB;

import java.util.ArrayList;

public class NewsFeeds extends AppCompatActivity {
    EditText txtDate, txtNews, txtOwner, txtTime;
    NewsDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds);

        db = new NewsDB(this);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayList detail = getListData();
        final ListView lv = (ListView) findViewById(R.id.newslistview);
        lv.setAdapter(new CustomListAdapter_News(this, detail));
    }

    private ArrayList getListData() {
        ArrayList<NewsBL> results = new ArrayList<NewsBL>();
        Cursor cursor = db.DisplayAll();

        if (cursor.moveToFirst()) {
            do {
                NewsBL newsData = new NewsBL();
                newsData.setId(cursor.getString(0));
                newsData.setNews(cursor.getString(1));
                newsData.setDate(cursor.getString(2));
                newsData.setTime(cursor.getString(3));
                newsData.setOwner(cursor.getString(4));
                results.add(newsData);
            } while (cursor.moveToNext());
        }

        return results;
    }

    public void SaveNews(View view){
        String news = txtNews.getText().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String owner = txtOwner.getText().toString();

        NewsBL n = new NewsBL(news,date,time,owner);
        db.add(n);

        Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
    }
}
