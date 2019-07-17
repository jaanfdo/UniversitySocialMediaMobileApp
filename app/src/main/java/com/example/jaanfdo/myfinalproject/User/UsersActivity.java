package com.example.jaanfdo.myfinalproject.User;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jaanfdo.myfinalproject.R;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    UserDB database;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        database = new UserDB(this);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayList details = getListData();
        final ListView lv = (ListView) findViewById(R.id.userlistview);
        lv.setAdapter(new UserAdapter(this, details));

    }

    public ArrayList getListData(){
        ArrayList<Users> results = new ArrayList<Users>();
        Cursor cursor = database.DisplayAll();

        if (cursor.getCount() == 0)
        {
            return results;
        }

        if (cursor.moveToFirst()) {
            do {
                Users value = new Users();
                value.setId(Integer.parseInt(cursor.getString(0)));
                value.setUsername(cursor.getString(1));
                value.setPassword(cursor.getString(2));
                value.setEmail(cursor.getString(3));
                results.add(value);

            } while (cursor.moveToNext());
        }

        return results;
    }

}
