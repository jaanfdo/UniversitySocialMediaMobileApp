package com.example.jaanfdo.myfinalproject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Events;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_News;
import com.example.jaanfdo.myfinalproject.Database.EventsDB;
import com.example.jaanfdo.myfinalproject.Database.NewsDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsFeeds extends AppCompatActivity {
    EditText txtDate, txtNews, txtOwner, txtTime;
    NewsDB db;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds);

        //db = new NewsDB(this);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //ArrayList detail = get
        ListData();
        lv = (ListView) findViewById(R.id.newslistview);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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

    public void ListData(){
        final ArrayList<NewsBL> results = new ArrayList<NewsBL>();
        String URL = "http://192.168.1.3:1234/AndroidPHP/server3.php?Action=viewNews";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONObject jsonObject = new JSONObject(response);
                            //System.out.println("List : " + jsonObject);
                            JSONArray dataArray = new JSONArray(response);
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                NewsBL newsData = new NewsBL();

                                newsData.setId(dataobj.getString("id"));
                                newsData.setNews(dataobj.getString("news"));
                                newsData.setDate(dataobj.getString("date"));
                                newsData.setTime(dataobj.getString("time"));
                                newsData.setOwner(dataobj.getString("owner"));
                                results.add(newsData);
                            }

                            lv.setAdapter(new CustomListAdapter_News(getApplicationContext(), results));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

//        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        //return results;
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
