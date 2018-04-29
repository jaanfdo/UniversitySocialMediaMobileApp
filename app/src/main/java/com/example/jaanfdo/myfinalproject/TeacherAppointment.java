package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.jaanfdo.myfinalproject.Database.Appointment;

public class TeacherAppointment extends AppCompatActivity {

    Button button;
    String [] listarray = {"Samsung", "Nokia", "Apple", "Microsoft", "Oppo","HTC","Sony","Lenova","Asus","Pixel"};
    ArrayAdapter adapter;
    ListView lv;
    Appointment db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_appointment);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        /*adapter = new ArrayAdapter<String>(this, R.layout.activity_teacher_appointment, listarray);
        lv = (ListView) findViewById(R.id.teacherappointmentlistview);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);*/

        button = (Button) findViewById(R.id.btnAddTeacherAppointment);


        //populateListView();
    }

    private void populateListView(){
        Cursor c = db.DisplayAllDetails();
        String[] fromFieldNames = new String[]{c.getString(2),"date"};
        int[] toVieIDs = new int[] {R.id.textView, R.id.textView2};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_class_schedule,c,fromFieldNames,toVieIDs,0);
        ListView mylist = (ListView) findViewById(R.id.schedulelistview);
        mylist.setAdapter(cursorAdapter);
    }

    public void AddSchedule(View view){
        Intent i = new Intent(this, AddTeacherAppointment.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_form, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

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

}
