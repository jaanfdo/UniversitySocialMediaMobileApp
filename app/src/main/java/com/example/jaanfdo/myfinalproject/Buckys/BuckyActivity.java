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

import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
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
        long status = dbHandler.addProducts(product);
        if(status > 0){
            getListData();
            Toast.makeText(getApplicationContext(), "Record Added" + bucky, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteButtonClicked(View v){
        String inputText = buckyInput.getText().toString();
        dbHandler.deleteProducts(inputText);
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

    private ArrayList getListData() {
        ArrayList<Products> results = new ArrayList<Products>();
        Cursor cursor = dbHandler.DisplayAllDetails();

        if (cursor.moveToFirst()) {
            do {
                Products product = new Products();
                product.set_id(cursor.getInt(0));
                product.set_productname(cursor.getString(1));
                results.add(product);
            } while (cursor.moveToNext());
        }

        return results;
    }

    public  void  btnAlert(View view){
        Intent i = new Intent(this, AlertMessage.class);
        startActivity(i);
    }


    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        buckyInput.setText(dbString);
        adapter.add(dbString);
        adapter.notifyDataSetChanged();
        buckyText.setText("");
    }
}
