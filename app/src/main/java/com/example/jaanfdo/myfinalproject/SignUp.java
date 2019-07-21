package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.BusinessClass.SignUpBL;
import com.example.jaanfdo.myfinalproject.Database.SignUpDB;

public class SignUp extends AppCompatActivity {

    SignUpDB db;
    EditText UserName, Password, Email, UniCode;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new SignUpDB(this, null, null, 1);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        UserName = (EditText) findViewById(R.id.txtUserName);
        Password = (EditText) findViewById(R.id.txtPassword);
        Email = (EditText) findViewById(R.id.txtEmail);
        UniCode = (EditText) findViewById(R.id.txtUniCode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void SignUp(View view){
        String username = UserName.getText().toString();
        String password = Password.getText().toString();
        String email = Email.getText().toString();
        String unicode = UniCode.getText().toString();
        SignUpBL signbl = new SignUpBL(username,password,email,unicode);
        db.add(signbl);

        Toast.makeText(getApplicationContext(), "Sign Up Successfully" + signbl, Toast.LENGTH_SHORT).show();
    }

    public void lnkLoginClick(View view){
        Intent i = new Intent(SignUp.this, Login.class);
        startActivity(i);
    }

}
