package com.example.jaanfdo.myfinalproject.User;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jaanfdo.myfinalproject.R;

public class UsersActivity extends AppCompatActivity {

    UserDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        database = new UserDB(this);

    }

    public void ViewAll(){
        Cursor cursor = database.DisplayAll();
        if (cursor.getCount() == 0)
        {
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("Id : " + cursor.getString(0)+"\n");
            buffer.append("User Name : " + cursor.getString(1)+"\n");
            buffer.append("Password : " + cursor.getString(2)+"\n");
            buffer.append("Email : " + cursor.getString(3)+"\n\n");


        }




    }

}
