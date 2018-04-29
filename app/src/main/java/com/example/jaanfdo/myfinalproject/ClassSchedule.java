package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.Database.Schedule;

public class ClassSchedule extends AppCompatActivity {

    Button button;
    String [] listarray = {"Samsung", "Nokia", "Apple", "Microsoft", "Oppo","HTC","Sony","Lenova","Asus","Pixel"};
    ArrayAdapter adapter;
    ListView lv;
    Schedule db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //populateListView();

        /*adapter = new ArrayAdapter<String>(this, R.layout.activity_class_schedule, listarray);
        lv = (ListView) findViewById(R.id.schedulelistview);
        lv.setAdapter(adapter);*/
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id_To_Search = i + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Toast.makeText(ClassSchedule.this, "Click List Item", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getApplicationContext(), AddClassSchedule.class);

                intent.putExtras(dataBundle);
                startActivity(intent);

                Bundle passdata = new Bundle();
                Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
                int nameId = listCursor.getInt(listCursor
                        .getColumnIndex(helper_ob.KEY_ID));
                passdata.putInt("keyid", nameId);
                Intent passIntent = new Intent(this, EditActivity.class);
                passIntent.putExtras(passdata);
                startActivity(passIntent);*/
           /* }
        });
        registerForContextMenu(lv);*/

        button = (Button) findViewById(R.id.btnAddClassSchedule);
    }

    private void populateListView(){
        Cursor c = db.DisplayAllDetails();
        String[] fromFieldNames = new String[]{c.getString(2),c.getString(3)};
        int[] toVieIDs = new int[] {R.id.textView, R.id.textView2};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_class_schedule,c,fromFieldNames,toVieIDs,0);
        ListView mylist = (ListView) findViewById(R.id.schedulelistview);
        mylist.setAdapter(cursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_form, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }


    public void AddSchedule(View view){
        Intent i = new Intent(this, AddClassSchedule.class);
        startActivity(i);
    }

}
