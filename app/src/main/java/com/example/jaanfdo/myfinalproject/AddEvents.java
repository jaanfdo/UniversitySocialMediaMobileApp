package com.example.jaanfdo.myfinalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.Database.EventsDB;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddEvents extends AppCompatActivity {

    DateFormat df = DateFormat.getDateTimeInstance();
    Calendar cal = Calendar.getInstance();
    EditText txtDate, txtTime,eventName, place, description;
    TextView txtID;
    Spinner CourseSpinner;
    ArrayList<String> name =  new ArrayList<>();
    EventsDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Spinner spinner = (Spinner) findViewById(R.id.CourseSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        txtID = (TextView) findViewById(R.id.EventID);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        eventName = (EditText) findViewById(R.id.txtEventName);
        CourseSpinner = (Spinner) findViewById(R.id.CourseSpinner);
        place = (EditText) findViewById(R.id.txtPlace);
        description = (EditText) findViewById(R.id.txtDescription);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
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

    public void Save(View view){
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                String id= txtID.getText().toString();
                String course = CourseSpinner.getSelectedItem().toString();
                String date = txtDate.getText().toString();
                String time = txtTime.getText().toString();
                String eventname = eventName.getText().toString();
                String places = place.getText().toString();
                String descriptions = description.getText().toString();
                /*EventsBL obj = new EventsBL(id,eventname,course,date,time,places,descriptions);
                db.update(obj);*/

                Toast.makeText(getApplicationContext(), course + " " + date + " " + time + "Record Updated", Toast.LENGTH_SHORT).show();
            } else{
                /*String course = CourseSpinner.getSelectedItem().toString();
                String date = txtDate.getText().toString();
                String time = txtTime.getText().toString();
                String eventname = eventName.getText().toString();
                String places = place.getText().toString();
                String descriptions = description.getText().toString();*/
                //EventsBL obj = new EventsBL(eventname,course,date,time,places,descriptions);
                EventsBL obj = new EventsBL("Dance","Bsc(Hon) in SE","May 26, 2013" , "13:35","Negombo","");
                db.add(obj);

                Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Update(View view){
        String course = CourseSpinner.getSelectedItem().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String eventname = eventName.getText().toString();
        String places = place.getText().toString();
        String descriptions = description.getText().toString();
        /*EventsBL obj = new EventsBL(eventname,course,date,time,places,descriptions);
        db.add(obj);*/
        EventsBL obj = new EventsBL("Dance","Bsc(Hon) in SE","May 26, 2013" , "13:35","Negombo","");
        db.add(obj);

        Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
        /*Toast.makeText(getApplicationContext(), course + " " + date + " " + time + "Record Updated", Toast.LENGTH_SHORT).show();*/
    }
}
