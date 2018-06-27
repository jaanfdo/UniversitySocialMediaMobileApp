package com.example.jaanfdo.myfinalproject.WebService;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jaanfdo.myfinalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentActivity extends AppCompatActivity {
    JSONArray result;
    private ListView listView;
    StudentModel std = new StudentModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        /*List<Student> students = null;
        try {
            students = new HttpRequestProductList().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        listView = (ListView) findViewById(R.id.listViewStudent);
        //listView.setAdapter(new StudentListAdapter(students, getApplicationContext()));

        showEmployee();
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            result = new JSONArray(std.findAll());
            System.out.println("*****Array*****" + result.length());
            System.out.println("*****Json*****" + result);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                Integer id = Integer.valueOf(jo.getString("sid"));
                String name = jo.getString("sName");

                HashMap<String,String> employees = new HashMap<>();
                employees.put(String.valueOf(id), "sid");
                employees.put(name, "sName");
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.studentlayout,
                new String[]{"sid","sName"},
                new int[]{R.id.textViewID, R.id.textViewName});

        listView.setAdapter(adapter);
    }

    /*private class HttpRequestProductList extends AsyncTask<Void, Void, List<Student>> {

        @Override
        protected List<Student> doInBackground(Void... voids) {
            StudentModel studentModel = new StudentModel();
            return  studentModel.findAll();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
        }
    }*/
}
