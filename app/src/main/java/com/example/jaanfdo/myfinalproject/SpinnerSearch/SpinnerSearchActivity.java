package com.example.jaanfdo.myfinalproject.SpinnerSearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.R;

public class SpinnerSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_search);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_search);
        // Creating ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mobile_manufacturers, android.R.layout.simple_spinner_item);
        // Specify layout to be used when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to our spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        switch (selectedItem) {
            case "Select one Item":
                break;
            case "Samsung":
                Toast.makeText(getApplicationContext(), selectedItem + " in Korean means 3 Stars!", Toast.LENGTH_SHORT).show();
                break;
            case "Foxconn":
                Toast.makeText(getApplicationContext(), selectedItem + " is world's largest contract electronics manufacturer", Toast.LENGTH_SHORT).show();
                break;
            case "Apple":
                Toast.makeText(getApplicationContext(), selectedItem + " was founded in 1976!", Toast.LENGTH_SHORT).show();
                break;
            case "Oppo":
                Toast.makeText(getApplicationContext(), selectedItem + " made first phone that can make 50MP photos", Toast.LENGTH_SHORT).show();
                break;
            case "Nokia":
                Toast.makeText(getApplicationContext(), selectedItem + " was founded 151 years ago!", Toast.LENGTH_SHORT).show();
                break;
            case "LYF":
                Toast.makeText(getApplicationContext(), selectedItem + " is an Indian Mobile Handset company", Toast.LENGTH_SHORT).show();
                break;
            case "Xiaomi":
                Toast.makeText(getApplicationContext(), selectedItem + " is world's 4th largest smartphone maker!", Toast.LENGTH_SHORT).show();
                break;
            case "Huawei":
                Toast.makeText(getApplicationContext(), selectedItem + " is largest telecommunications equipment manufacturer in the World! ", Toast.LENGTH_SHORT).show();
                break;
            case "Asus":
                Toast.makeText(getApplicationContext(), selectedItem + " name came from word PEGASUS", Toast.LENGTH_SHORT).show();
                break;
            case "Lenovo":
                Toast.makeText(getApplicationContext(), selectedItem + " is largest PC vendor by unit sales", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
