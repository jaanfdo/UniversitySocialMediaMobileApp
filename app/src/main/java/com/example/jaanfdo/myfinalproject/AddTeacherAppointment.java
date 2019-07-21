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

import com.example.jaanfdo.myfinalproject.BusinessClass.TeacherAppointmentBL;
import com.example.jaanfdo.myfinalproject.Database.AppointmentDB;

import java.text.DateFormat;
import java.util.Calendar;

public class AddTeacherAppointment extends AppCompatActivity {

    DateFormat df = DateFormat.getDateTimeInstance();
    Calendar cal = Calendar.getInstance();
    EditText txtDate, txtTime, txtReason, txtLecturer;
    Spinner CourseSpinner;
    TextView AppointmentID;
    AppointmentDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_appointment);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Spinner spinner = (Spinner) findViewById(R.id.CourseSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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

    public void SaveSchedule(View view){
//        String course = CourseSpinner.getSelectedItem().toString();
//        String date = txtDate.getText().toString();
//        String time = txtTime.getText().toString();
//        String lecturer = txtLecturer.getText().toString();
//        String reason = txtReason.getText().toString();
//        TeacherAppointmentBL appointment = new TeacherAppointmentBL(course,date,time,lecturer,reason);
//        db.add(appointment);

        Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();
    }
    public void UpdateSchedule(View view){
        String id = AppointmentID.getText().toString();
        String course = CourseSpinner.getSelectedItem().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String lecturer = txtLecturer.getText().toString();
        String reason = txtReason.getText().toString();
        TeacherAppointmentBL appointment = new TeacherAppointmentBL(id,course,date,time,lecturer,reason);
        db.update(appointment);

        Toast.makeText(getApplicationContext(), "Record Updated" , Toast.LENGTH_SHORT).show();
    }
    public void DeleteSchedule(View view) {
        String id = AppointmentID.getText().toString();
        TeacherAppointmentBL appointment = new TeacherAppointmentBL(id);
        db.delete(appointment);


        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
    }
}
