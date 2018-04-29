package com.example.jaanfdo.myfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.Database.NewsDB;

public class NewsFeeds extends AppCompatActivity {
    EditText txtDate, txtNews, txtOwner, txtTime;
    NewsDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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
