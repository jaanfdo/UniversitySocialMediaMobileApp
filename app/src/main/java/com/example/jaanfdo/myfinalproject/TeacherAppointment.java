package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.TeacherAppointmentBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Appointment;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Schedule;
import com.example.jaanfdo.myfinalproject.Database.AppointmentDB;
import com.example.jaanfdo.myfinalproject.Database.ScheduleDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeacherAppointment extends AppCompatActivity {

    Button button;
    //String [] listarray = {"Samsung", "Nokia", "Apple", "Microsoft", "Oppo","HTC","Sony","Lenova","Asus","Pixel"};
    //ArrayAdapter adapter;
    //ListView lv;
    AppointmentDB db;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_appointment);

        //db = new AppointmentDB(this);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //ArrayList detail = getListData();

         lv = (ListView) findViewById(R.id.teacherappointmentlistview);
        ListData();

        button = (Button) findViewById(R.id.btnAddTeacherAppointment);
    }

    public ArrayList<TeacherAppointmentBL> getListData() {
        ArrayList<TeacherAppointmentBL> array_list = new ArrayList<TeacherAppointmentBL>();

        Cursor c = db.DisplayAll();
        while(c.isAfterLast() == false){
            TeacherAppointmentBL detail = new TeacherAppointmentBL();

            detail.setCourse(c.getString(c.getColumnIndex("course")));
            detail.setLecturer(c.getString(c.getColumnIndex("lecturer")));
            detail.setDate(c.getString(c.getColumnIndex("date")));
            detail.setTime(c.getString(c.getColumnIndex("time")));
            detail.setReason(c.getString(c.getColumnIndex("reason")));
            detail.setUser(c.getString(c.getColumnIndex("user")));

            array_list.add(detail);
            c.moveToNext();
        }
        return array_list;
    }

    public void ListData(){
        final ArrayList<TeacherAppointmentBL> results = new ArrayList<TeacherAppointmentBL>();
        String URL = "http://192.168.1.3:1234/AndroidPHP/server3.php?Action=viewAppointment";
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

                                TeacherAppointmentBL detail = new TeacherAppointmentBL();
                                detail.setId(dataobj.getString("id"));
                                detail.setCourse(dataobj.getString("course"));
                                detail.setLecturer(dataobj.getString("lecturer"));
                                detail.setDate(dataobj.getString("date"));
                                detail.setTime(dataobj.getString("time"));
                                detail.setReason(dataobj.getString("reason"));
                                detail.setUser(dataobj.getString("owner"));

                                results.add(detail);

                            }

                            System.out.println("ArrayList TeacherAppointmentBL : " + results);
                            lv.setAdapter(new CustomListAdapter_Appointment(getApplicationContext(), results));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void AddSchedule(View view){
        Intent i = new Intent(this, AddTeacherAppointment.class);
        startActivity(i);
    }

}















//        adapter = new ArrayAdapter<String>(this, R.layout.activity_teacher_appointment, listarray);
//        lv = (ListView) findViewById(R.id.teacherappointmentlistview);
//        lv.setAdapter(adapter);

    /*public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(AlertMessage.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/


//private void populateListView(){
//        Cursor c = db.DisplayAllDetails();
//        String[] fromFieldNames = new String[]{c.getString(2),"date"};
//        int[] toVieIDs = new int[] {R.id.textView, R.id.textView2};
//        SimpleCursorAdapter cursorAdapter;
//        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_class_schedule,c,fromFieldNames,toVieIDs,0);
//        ListView mylist = (ListView) findViewById(R.id.schedulelistview);
//        mylist.setAdapter(cursorAdapter);
//}