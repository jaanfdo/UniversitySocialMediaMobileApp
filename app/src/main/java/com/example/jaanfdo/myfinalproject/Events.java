package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Events;
import com.example.jaanfdo.myfinalproject.Database.EventsDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Events extends AppCompatActivity {
    EventsDB database;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //database = new EventsDB(this);
        
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //ArrayList detail = getListData();
        lv = (ListView) findViewById(R.id.eventlistview);
        ListData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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

    public void ListData(){
        final ArrayList<EventsBL> results = new ArrayList<EventsBL>();
        String URL = "http://192.168.1.3:1234/AndroidPHP/server3.php?Action=viewEvents";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("Response : " + response);
                            //JSONObject jsonObject = new JSONObject(response);
                            //System.out.println("List : " + jsonObject);

                            JSONArray dataArray = new JSONArray(response);
                            for (int i = 0; i < dataArray.length(); i++) {
                                //System.out.println("JSONObject : " + dataArray);

                                JSONObject dataobj = dataArray.getJSONObject(i);
                                //System.out.println("JSONObject 2 : " + dataobj);

                                EventsBL detail = new EventsBL();
                                detail.setId(dataobj.getString("id"));
                                detail.setEventname(dataobj.getString("eventname"));
                                detail.setCourse(dataobj.getString("course"));
                                detail.setDate(dataobj.getString("date"));
                                detail.setTime(dataobj.getString("time"));
                                detail.setPlace(dataobj.getString("place"));

                                results.add(detail);

                            }

                            System.out.println("ArrayList ScheduleBL : " + results);
                            lv.setAdapter(new CustomListAdapter_Events(getApplicationContext(), results));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void addEvents(View view){
        Intent i = new Intent(this, AddEvents.class);
        startActivity(i);
    }

}
