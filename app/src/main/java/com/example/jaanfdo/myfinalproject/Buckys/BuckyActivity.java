package com.example.jaanfdo.myfinalproject.Buckys;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.Employee.ViewEmployeeActivity;
import com.example.jaanfdo.myfinalproject.Hero.HerosActivity;
import com.example.jaanfdo.myfinalproject.Employee.EmployeeHomeActivity;
import com.example.jaanfdo.myfinalproject.R;

import java.util.ArrayList;

public class BuckyActivity extends AppCompatActivity {

    EditText buckyInput;
    TextView buckyText;
    ListView buckylistView;
    MyDBHandler dbHandler;
    ArrayList<String> name =  new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucky);

        buckylistView = (ListView) findViewById(R.id.buckylistView);
        buckyInput = (EditText) findViewById(R.id.txtInput);
        buckyText = (TextView) findViewById(R.id.buckyText);
        buckylistView = (ListView) findViewById(R.id.buckylistView);
        dbHandler = new MyDBHandler(this, null, null, 1);

        final ArrayList image_details = getListData();
        //final ListView lv = (ListView) findViewById(R.id.eventlistview);
        //lv.setAdapter(new ListViewThemeEvents(this, image_details));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,  image_details);
        buckylistView.setAdapter(adapter);

        buckylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //buckyInput.setText((Integer) image_details.get(i));

                Toast.makeText(BuckyActivity.this, "Clicked" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addButtonClicked(View v){
        String bucky = buckyInput.getText().toString();
        Products product = new Products(bucky);
        dbHandler.addProducts(product);
        //printDatabase();
        getListData();
        Toast.makeText(getApplicationContext(), "Record Added" + bucky, Toast.LENGTH_SHORT).show();
    }

    public void deleteButtonClicked(View v){
        String inputText = buckyInput.getText().toString();
        dbHandler.deleteProducts(inputText);
        //printDatabase();
        getListData();
        Toast.makeText(getApplicationContext(), "Record Deleted" + inputText, Toast.LENGTH_SHORT).show();
    }

    public  void  updateButtonClicked(View v){
        String inputText = buckyInput.getText().toString();
        dbHandler.updateProducts(inputText);
        //printDatabase();
        getListData();
        Toast.makeText(getApplicationContext(), "Record Update" + inputText, Toast.LENGTH_SHORT).show();
    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        buckyInput.setText(dbString);
        adapter.add(dbString);
        adapter.notifyDataSetChanged();
        buckyText.setText("");
    }

    private ArrayList getListData() {
        ArrayList<Products> results = new ArrayList<Products>();
        Cursor c = dbHandler.DisplayAllDetails();
        String[] field = new String[]{c.getString(1)};
        Products product = new Products();
        product.set_productname(c.getString(1));

        /*String[] fromFieldNames = new String[]{c.getString(2),c.getString(3)};
        int[] toVieIDs = new int[] {R.id.txtOwner, R.id.txtNews};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_class_schedule,c,fromFieldNames,toVieIDs,0);
        ListView mylist = (ListView) findViewById(R.id.schedulelistview);
        mylist.setAdapter(cursorAdapter);*/

        /*newsData.setOwner("Bsc (Hon) in Softwar Eniginering");
        newsData.setEventname("Dance of Democracy");
        newsData.setCourse("Bsc (Hon) in Softwar Eniginering");
        newsData.setDescription("Bsc (Hon) in Softwar Eniginering");
        newsData.setDate("May 26, 2013");
        newsData.setTime("13:35");
        newsData.setPlace("Park Plaza Hotel");*/
        results.add(product);

        return results;
    }

    public void next(View view){
        Intent i=new Intent(this, EmployeeHomeActivity.class);
        startActivity(i);
    }

    public void nextList(View view){
        Intent i=new Intent(this, ViewEmployeeActivity.class);
        startActivity(i);
    }

    public void btnHero(View view){
        Intent i = new Intent(this,HerosActivity.class);
        startActivity(i);
    }

    public  void  btnAlert(View view){
        Intent i = new Intent(this, AlertMessage.class);
        startActivity(i);
    }
}
