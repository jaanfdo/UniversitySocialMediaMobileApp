package com.example.jaanfdo.myfinalproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Schedule;
import com.example.jaanfdo.myfinalproject.Database.ScheduleDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by jaanfdo on 3/11/2017.
 */

public class ScheduleFragment extends Fragment {
    ScheduleDB db;
    ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_schedule_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.schedulelistview);
        ListData();
        return view;
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
                            lv.setAdapter(new ListviewContactAdapter3(getActivity(), results));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }

}

class ListviewContactAdapter3 extends BaseAdapter {
    private static ArrayList<ScheduleBL> listData;
    private LayoutInflater mInflater;

    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;
    TextView view5;
    TextView view6;

    public ListviewContactAdapter3(Context photosFragment, ArrayList<ScheduleBL> results){
        listData = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listData.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.customlist_adapter_schedule, null);

        view1 = (TextView) convertView.findViewById(R.id.view1);
        view2 = (TextView) convertView.findViewById(R.id.view2);
        view3 = (TextView) convertView.findViewById(R.id.view3);
        view4 = (TextView) convertView.findViewById(R.id.view4);
        view5 = (TextView) convertView.findViewById(R.id.view5);
        view6 = (TextView) convertView.findViewById(R.id.view6);

        view1.setText("ID " + listData.get(position).getId());
        view2.setText("Course " + listData.get(position).getCourse());
        view4.setText("Subject " + listData.get(position).getSubject());
        view6.setText("Class Date " + listData.get(position).getDate() + " " + listData.get(position).getDate());
        view3.setText("Place & Room " + listData.get(position).getClassfloor() + " " +  listData.get(position).getClassno());
        view5.setText("Lecturer " + listData.get(position).getLecname());

        return convertView;
    }

}





















//    static class ViewHolder{
//        TextView txtname, txtphone;
//    }

// TODO Auto-generated method stub
//        ViewHolder holder;
//        if(convertView == null){
//            convertView = mInflater.inflate(R.layout.listviewtheme, null);
//            holder = new ViewHolder();
//            holder.txtname = (TextView) convertView.findViewById(R.id.textView);
//            holder.txtphone = (TextView) convertView.findViewById(R.id.textView2);
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.txtname.setText(listContact.get(position).getLecname());
//        holder.txtphone.setText(listContact.get(position).getSubject());


//        ArrayList<ScheduleBL> listContact = GetlistContact();
//        ListView lv = (ListView)view.findViewById(R.id.schedulelistview);
//        lv.setAdapter(new ListviewContactAdapter3(getActivity(), listContact));


//    private ArrayList<ScheduleBL> GetlistContact(){
//        ArrayList<ScheduleBL> contactlist = new ArrayList<ScheduleBL>();
//
//        ScheduleBL obj = null;
//
//        obj = new ScheduleBL();
//        obj.setLecname("aa aaaa");
//        obj.setCourse("aaaaaaaaaa");
//        contactlist.add(obj);
//
//        obj = new ScheduleBL();
//        obj.setLecname("ssss sssss");
//        obj.setCourse("ssss sssss sssssss sssss");
//        contactlist.add(obj);
//
//        obj = new ScheduleBL();
//        obj.setLecname("ddd dddd dddddd");
//        obj.setCourse("ddddddddd ddddddd dddddd");
//        contactlist.add(obj);
//
//        return contactlist;
//    }