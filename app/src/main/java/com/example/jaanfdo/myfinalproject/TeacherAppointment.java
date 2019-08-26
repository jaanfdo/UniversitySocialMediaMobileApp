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

import com.example.jaanfdo.myfinalproject.BusinessClass.TeacherAppointmentBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Appointment;
import com.example.jaanfdo.myfinalproject.Database.AppointmentDB;
import com.example.jaanfdo.myfinalproject.Database.ScheduleDB;

import java.util.ArrayList;

public class TeacherAppointment extends AppCompatActivity {

    Button button;
    //String [] listarray = {"Samsung", "Nokia", "Apple", "Microsoft", "Oppo","HTC","Sony","Lenova","Asus","Pixel"};
    //ArrayAdapter adapter;
    //ListView lv;
    AppointmentDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_appointment);

        db = new AppointmentDB(this);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayList detail = getListData();
        final ListView lv = (ListView) findViewById(R.id.teacherappointmentlistview);
        lv.setAdapter(new CustomListAdapter_Appointment(this, detail));

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