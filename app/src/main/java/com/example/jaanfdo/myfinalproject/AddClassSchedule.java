package com.example.jaanfdo.myfinalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.Database.Schedule;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddClassSchedule extends AppCompatActivity {

    DateFormat df = DateFormat.getDateTimeInstance();
    Calendar cal = Calendar.getInstance();
    EditText txtDate, txtLecName, txtClassNo, txtClassFloor, txtTime, txtSubject;
    TextView ShceduleID;
    Button button;
    Spinner CourseSpinner;
    ArrayList<String> name =  new ArrayList<>();
    Schedule db;


    //get list data
    int rowId;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_schedule);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Spinner spinner = (Spinner) findViewById(R.id.CourseSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button = (Button) findViewById(R.id.btnSaveClassSchedule);

        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDisplay();
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeDisplay();
            }
        });


        /*Bundle showData = getIntent().getExtras();
        rowId = showData.getInt("keyid");
        // Toast.makeText(getApplicationContext(), Integer.toString(rowId),
        // 500).show();
        regadapter = new RegistrationAdapter(this);

        c = regadapter.queryAll(rowId);

        if (c.moveToFirst()) {
            do {
                fname.setText(c.getString(1));
                lname.setText(c.getString(2));

            } while (c.moveToNext());
        }*/
    }
    private void DateDisplay(){
        new DatePickerDialog(this, d, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void TimeDisplay(){
        new TimePickerDialog(this, t, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            cal.set(Calendar.YEAR, i);
            cal.set(Calendar.MONTH, i1);
            cal.set(Calendar.DAY_OF_MONTH, i2);

            txtDate.setText(df.format(cal.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            cal.set(Calendar.HOUR_OF_DAY, i);
            cal.set(Calendar.MINUTE, i1);

            txtTime.setText(df.format(cal.getTime()));
        }
    };


    public void SaveSchedule(View view){
        String course = CourseSpinner.getSelectedItem().toString();
        String subject = txtSubject.getText().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String classno = txtClassNo.getText().toString();
        String classfloor = txtClassFloor.getText().toString();
        String lecname = txtLecName.getText().toString();

        ScheduleBL schedule = new ScheduleBL(course, subject,date,time,classfloor,classno,lecname);
        db.create(schedule);

        Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
    }
    public void UpdateSchedule(View view){
        String id = ShceduleID.getText().toString();
        String course = CourseSpinner.getSelectedItem().toString();
        String subject = txtSubject.getText().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String classno = txtClassNo.getText().toString();
        String classfloor = txtClassFloor.getText().toString();
        String lecname = txtLecName.getText().toString();
        ScheduleBL schedule = new ScheduleBL(id,course, subject,date,time,classfloor,classno,lecname);
        db.update(schedule);

        Toast.makeText(getApplicationContext(), "Record Updated" , Toast.LENGTH_SHORT).show();
    }
    public void DeleteSchedule(View view) {
        String id = ShceduleID.getText().toString();
        ScheduleBL schedule = new ScheduleBL(id);
        db.delete(schedule);


        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
    }
}
