package com.example.jaanfdo.myfinalproject.Employee;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewEmployee extends AppCompatActivity implements AdapterView.OnItemClickListener {
    JSONArray result;
    ListView listView;
    BioData biodata = new BioData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        if  ( android.os.Build.VERSION.SDK_INT  >  9 )  {
            StrictMode.ThreadPolicy policy =  new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        showEmployee();
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            //jsonObject = new JSONObject(JSON_STRING);
            result = new JSONArray(biodata.tampilBiodata());
            //arrayBiodata = new JSONArray(biodata.tampilBiodata());
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String id = jo.getString("id");
                String name = jo.getString("alamat");

                HashMap<String,String> employees = new HashMap<>();
                employees.put("id",id);
                employees.put("alamat",name);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewEmployee.this, list, R.layout.listview,
                new String[]{"id","alamat"},
                new int[]{R.id.newsOwner, R.id.newsNews});

        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ViewEmployee.this, "Clicked" + view.getId() +"/"+ position, Toast.LENGTH_SHORT).show();
    }
}
