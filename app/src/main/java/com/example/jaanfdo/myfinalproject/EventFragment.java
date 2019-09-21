package com.example.jaanfdo.myfinalproject;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Events;
import com.example.jaanfdo.myfinalproject.Database.EventsDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 3/11/2017.
 */

public class EventFragment extends Fragment {
    EventsDB db;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_event_fragment, container, false);
        db = new EventsDB(this.getContext());

       // ArrayList detail =
         ListData();
        lv = (ListView) view.findViewById(R.id.eventlistview);
        //lv.setAdapter(new ListviewContactAdapter(getActivity(), detail));

        return view;
    }

    private ArrayList getListData() {
        ArrayList<EventsBL> results = new ArrayList<EventsBL>();
        Cursor cursor = db.DisplayAll();

        if (cursor.moveToFirst()) {
            do {
                EventsBL newsData = new EventsBL();
                newsData.setId(cursor.getString(0));
                newsData.setEventname(cursor.getString(1));
                newsData.setCourse(cursor.getString(2));
                newsData.setDate(cursor.getString(3));
                newsData.setTime(cursor.getString(4));
                newsData.setPlace(cursor.getString(5));

                results.add(newsData);

            } while (cursor.moveToNext());
        }

        return results;
    }

    public void ListData(){
        final ArrayList<EventsBL> results = new ArrayList<EventsBL>();
        String URL = "http://192.168.1.3:1234/AndroidPHP/server3.php?Action=viewEvents";
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

                                EventsBL detail = new EventsBL();
                                detail.setId(dataobj.getString("id"));
                                detail.setEventname(dataobj.getString("eventname"));
                                detail.setCourse(dataobj.getString("course"));
                                detail.setDate(dataobj.getString("date"));
                                detail.setTime(dataobj.getString("time"));
                                detail.setPlace(dataobj.getString("place"));

                                results.add(detail);

                            }

                            System.out.println("ArrayList ScheduleBL : " + results);
                            lv.setAdapter(new ListviewContactAdapter(getActivity(), results));

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

class ListviewContactAdapter extends BaseAdapter {
    private static ArrayList<EventsBL> listData;
    private LayoutInflater mInflater;

    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;
    TextView view5;

    public ListviewContactAdapter(Context fragment, ArrayList<EventsBL> results){
        listData = results;
        mInflater = LayoutInflater.from(fragment);
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
        // TODO Auto-generated method stub

        convertView = mInflater.inflate(R.layout.customlist_adapter_events, null);

        view1 = (TextView) convertView.findViewById(R.id.view1);
        view2 = (TextView) convertView.findViewById(R.id.view2);
        view3 = (TextView) convertView.findViewById(R.id.view3);
        view4 = (TextView) convertView.findViewById(R.id.view4);
        view5 = (TextView) convertView.findViewById(R.id.view5);

        view1.setText("ID " + listData.get(position).getId());
        view2.setText("Event " + listData.get(position).getEventname());
        view3.setText("Course " + listData.get(position).getCourse());
        view4.setText("Event Date " + listData.get(position).getDate() + " " + listData.get(position).getDate());
        view5.setText("Place " + listData.get(position).getPlace());

        return convertView;
    }

}





















//    static class ViewHolder{
//        TextView txtname, txtphone;
//    }

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
//        holder.txtname.setText(listContact.get(position).getEventname());
//        holder.txtphone.setText(listContact.get(position).getDescription());



//        ArrayList<EventsBL> listContact = GetlistContact();
//        ListView lv = (ListView)view.findViewById(R.id.eventlistview);
//        lv.setAdapter(new ListviewContactAdapter(getActivity(), listContact));

//    private ArrayList<EventsBL> GetlistContact(){
//        ArrayList<EventsBL> contactlist = new ArrayList<EventsBL>();
//
//        EventsBL obj = null;
//
//        obj = new EventsBL();
//        obj.setEventname("Toppp pphee eer");
//        obj.setDescription("aaaaa aaaaa");
//        contactlist.add(obj);
//
//        obj = new EventsBL();
//        obj.setEventname("Beee eewo sss");
//        obj.setDescription("sssss sssssss ssss sssss");
//        contactlist.add(obj);
//
//        obj = new EventsBL();
//        obj.setEventname("Ruu uuuu waaa");
//        obj.setDescription("ssssssssssdddddddd");
//        contactlist.add(obj);
//
//        return contactlist;
//    }