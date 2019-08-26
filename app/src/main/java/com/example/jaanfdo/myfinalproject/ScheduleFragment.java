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

import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_Schedule;
import com.example.jaanfdo.myfinalproject.Database.ScheduleDB;

import java.util.ArrayList;


/**
 * Created by jaanfdo on 3/11/2017.
 */

public class ScheduleFragment extends Fragment {
    ScheduleDB db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_schedule_fragment, container, false);

        ArrayList detail = getListData();
        final ListView lv = (ListView) view.findViewById(R.id.schedulelistview);
        lv.setAdapter(new ListviewContactAdapter3(getActivity(), detail));

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