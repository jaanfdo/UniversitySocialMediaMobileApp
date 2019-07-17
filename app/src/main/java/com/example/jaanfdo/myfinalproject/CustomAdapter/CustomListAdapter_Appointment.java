package com.example.jaanfdo.myfinalproject.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaanfdo.myfinalproject.BusinessClass.TeacherAppointmentBL;
import com.example.jaanfdo.myfinalproject.R;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/28/2017.
 */

public class CustomListAdapter_Appointment extends BaseAdapter {
    private ArrayList<TeacherAppointmentBL> listData;
    private LayoutInflater layoutInflater;

    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;
    TextView view5;
    TextView view6;

    public CustomListAdapter_Appointment(Context aContext, ArrayList<TeacherAppointmentBL> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.customlist_adapter_appointment, null);

        view1 = (TextView) convertView.findViewById(R.id.view1);
        view2 = (TextView) convertView.findViewById(R.id.view2);
        view3 = (TextView) convertView.findViewById(R.id.view3);
        view4 = (TextView) convertView.findViewById(R.id.view4);
        view5 = (TextView) convertView.findViewById(R.id.view5);
        view6 = (TextView) convertView.findViewById(R.id.view6);

        view1.setText("ID " + listData.get(position).getId());
        view2.setText("User " + listData.get(position).getUser());
        view4.setText("Course " + listData.get(position).getCourse());
        view5.setText("Reason " + listData.get(position).getReason());
        view6.setText("AppointmentDB Date " + listData.get(position).getDate() + " " + listData.get(position).getDate());
        view3.setText("Lecturer " + listData.get(position).getLecturer());


        return convertView;
    }
}
