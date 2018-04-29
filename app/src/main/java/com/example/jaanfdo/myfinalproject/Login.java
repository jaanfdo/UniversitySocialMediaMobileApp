package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

         username = (EditText)findViewById(R.id.UserName);
         password = (EditText)findViewById(R.id.Password);
    }



    public void login(View view) {
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            Toast.makeText(this, "User Name and Password is Correct", Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(this, MainForm.class);
            //startActivity(i);
        } else {
            Toast.makeText(this, "User Name and Password is Incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void RegistrationClick(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
