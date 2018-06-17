package com.example.jaanfdo.myfinalproject.WebService;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jaanfdo.myfinalproject.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        List<Student> students = null;
        try {
            students = new HttpRequestProductList().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        listView = (ListView) findViewById(R.id.listViewStudent);
        listView.setAdapter(new StudentListAdapter(students, getApplicationContext()));
    }

    private class HttpRequestProductList extends AsyncTask<Void, Void, List<Student>>{

        @Override
        protected List<Student> doInBackground(Void... voids) {
            StudentModel studentModel = new StudentModel();
            return  studentModel.findAll();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
        }
    }
}
