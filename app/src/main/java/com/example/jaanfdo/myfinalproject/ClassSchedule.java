package com.example.jaanfdo.myfinalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_News;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Schedule;
import com.example.jaanfdo.myfinalproject.Database.ScheduleDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClassSchedule extends AppCompatActivity {

    Button button;
    //String [] listarray = {"Samsung", "Nokia", "Apple", "Microsoft", "Oppo","HTC","Sony","Lenova","Asus","Pixel"};
    //ArrayAdapter adapter;
    ListView lv;
    ScheduleDB db;
    Spinner CourseSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        //db = new ScheduleDB(this);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //ArrayList detail = getListData();
        ListData();
        lv = (ListView) findViewById(R.id.schedulelistview);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View convertView, int i, long l) {
                ViewHolder holder = null;
                Log.v("ConvertView", String.valueOf(i));

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.customlist_adapter_schedule, null);

                    holder = new ViewHolder();
                    String val1 = convertView.findViewById(R.id.view1).toString();
                    String val2 = convertView.findViewById(R.id.view2).toString();
                    convertView.setTag(holder);

                    Toast.makeText(ClassSchedule.this, "Name: " + val1, Toast.LENGTH_LONG).show();

                } else {
                    holder = (ViewHolder) convertView.getTag();
                }


                //                int id_To_Search = i + 1;
//                String selectedItem = (String) adapterView.getItemAtPosition(i);
//
//                Bundle dataBundle = new Bundle();
//                dataBundle.putInt("id", id_To_Search);
//
//                Toast.makeText(ClassSchedule.this, "Click List Item " + i  + " - " + selectedItem.toString(), Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), AddClassSchedule.class);

//                intent.putExtras(dataBundle);
//                startActivity(intent);
//
//                Bundle passdata = new Bundle();
//                Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
//                int nameId = listCursor.getInt(listCursor
//                        .getColumnIndex(helper_ob.KEY_ID));
//                passdata.putInt("keyid", nameId);
//                Intent passIntent = new Intent(this, EditActivity.class);
//                passIntent.putExtras(passdata);
//                startActivity(passIntent);
           }
        });
          registerForContextMenu(lv);
    }

    private ArrayList getListData() {
        ArrayList<ScheduleBL> results = new ArrayList<ScheduleBL>();
        Cursor c = db.DisplayAll();

        if(c.moveToFirst()) {
            while (c.isAfterLast() == false) {
                ScheduleBL detail = new ScheduleBL();

                detail.setCourse(c.getString(c.getColumnIndex("course")));
                detail.setSubject(c.getString(c.getColumnIndex("subjects")));
                detail.setDate(c.getString(c.getColumnIndex("date")));
                detail.setTime(c.getString(c.getColumnIndex("time")));
                detail.setClassfloor(c.getString(c.getColumnIndex("class_floor")));
                detail.setClassno(c.getString(c.getColumnIndex("class_no")));
                detail.setLecname(c.getString(c.getColumnIndex("lecturer")));

                results.add(detail);
                c.moveToNext();
            }
        }

        return results;
    }

    public void ListData(){
        final ArrayList<ScheduleBL> results = new ArrayList<ScheduleBL>();
        String URL = "http://192.168.1.3:1234/AndroidPHP/server3.php?Action=viewSchedule";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("Response : " + response);
                            //JSONObject jsonObject = new JSONObject(response);
                            //System.out.println("List : " + jsonObject);

                            JSONArray dataArray = new JSONArray(response);
                            for (int i = 0; i < dataArray.length(); i++) {
                                //System.out.println("JSONObject : " + dataArray);

                                JSONObject dataobj = dataArray.getJSONObject(i);
                                //System.out.println("JSONObject 2 : " + dataobj);

                                ScheduleBL detail = new ScheduleBL();

                                detail.setId(dataobj.getString("id"));
                                detail.setCourse(dataobj.getString("course"));
                                detail.setSubject(dataobj.getString("subjects"));
                                detail.setDate(dataobj.getString("date"));
                                detail.setTime(dataobj.getString("time"));
                                detail.setClassfloor(dataobj.getString("class_floor"));
                                detail.setClassno(dataobj.getString("class_no"));
                                detail.setLecname(dataobj.getString("lecturer"));

                                results.add(detail);

                            }

                            System.out.println("ArrayList ScheduleBL : " + results);
                            lv.setAdapter(new CustomListAdapter_Schedule(getApplicationContext(), results));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void AddSchedule(View view){
        Add(view);
//        Intent i = new Intent(this, AddClassSchedule.class);
//        startActivity(i);
    }

    public void Add(View view){

        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.activity_add_class_schedule, null, false);

        final TextView editTextStudentScheduleID = (TextView) formElementsView.findViewById(R.id.ScheduleID);
        final EditText editTextStudentSubject = (EditText) formElementsView.findViewById(R.id.txtSubject);
        final EditText editTextStudentDate = (EditText) formElementsView.findViewById(R.id.txtDate);
        final EditText editTextStudentTime = (EditText) formElementsView.findViewById(R.id.txtTime);
        final EditText editTextStudentLecturer = (EditText) formElementsView.findViewById(R.id.txtLecName);
        final EditText editTextStudentClassFloor = (EditText) formElementsView.findViewById(R.id.txtClassFloor);
        final EditText editTextStudentClassNo = (EditText) formElementsView.findViewById(R.id.txtClassNo);
        final Spinner spinner = (Spinner) formElementsView.findViewById(R.id.CourseSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Student")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ScheduleBL schedule = new ScheduleBL();
                                //schedule.setId(editTextStudentScheduleID.getText().toString());
                                schedule.setCourse(spinner.getSelectedItem().toString());
                                schedule.setSubject(editTextStudentSubject.getText().toString());
                                schedule.setDate("2017-01-01");
                                schedule.setTime("12:00");
                                schedule.setLecname("Madam");
                                schedule.setClassfloor("2");
                                schedule.setClassno("4C");


                                boolean createSuccessful = db.create(schedule);
                                if(createSuccessful){
                                    Toast.makeText(getApplicationContext(), "Student information was saved.", Toast.LENGTH_SHORT).show();
                                    //readRecords();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                }
                                //dialog.cancel();
                            }

                        }).show();
    }

    public class ScheduleAdapter extends ArrayAdapter<ScheduleBL> {
        //our hero list
        List<ScheduleBL> List;

        //constructor to get the list
        public ScheduleAdapter(List<ScheduleBL> list) {
            super(ClassSchedule.this, R.layout.list_layout, list);
            this.List = list;
        }

        //method returning list item
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

            //getting the textview for displaying name
            TextView tvCourse = (TextView) listViewItem.findViewById(R.id.tvCourse);
            TextView tvSubject = (TextView) listViewItem.findViewById(R.id.tvSubject);
            TextView tvLecturer = (TextView) listViewItem.findViewById(R.id.tvLecturer);
            TextView tvDate = (TextView) listViewItem.findViewById(R.id.tvDate);
            TextView tvTime = (TextView) listViewItem.findViewById(R.id.tvTime);


            final ScheduleBL hero = List.get(position);
            tvCourse.setText(hero.getCourse());
            tvSubject.setText(hero.getSubject());
            tvLecturer.setText(hero.getLecname());
            tvDate.setText(hero.getDate());
            tvTime.setText(hero.getTime());

            return listViewItem;
        }
    }

    private class ViewHolder {
        TextView textView;
        Button button;
    }
}

















//    private void populateListView(){
//        Cursor c = null;
//        String[] fromFieldNames = new String[]{c.getString(2),c.getString(3)};
//        int[] toVieIDs = new int[] {R.id.textView, R.id.textView2};
//        SimpleCursorAdapter cursorAdapter;
//        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_class_schedule,c,fromFieldNames,toVieIDs,0);
//        ListView mylist = (ListView) findViewById(R.id.schedulelistview);
//        mylist.setAdapter(cursorAdapter);
//    }


//    public void readRecords() {
//        List<ScheduleBL> schedule = db.DisplayAllDetails();
//
//        if (schedule.size() > 0) {
//            ListView listView = (ListView) findViewById(R.id.schedulelistview);
//            ScheduleAdapter adapter = new ScheduleAdapter(schedule);
//            listView.setAdapter(adapter);
//        }
//    }

//readRecords();
//populateListView();

//        adapter = new ArrayAdapter<String>(this, R.layout.activity_class_schedule, listarray);
//        lv = (ListView) findViewById(R.id.schedulelistview);
//        lv.setAdapter(adapter);