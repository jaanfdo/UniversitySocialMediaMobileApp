package com.example.jaanfdo.myfinalproject.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.R;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/28/2017.
 */

public class CustomListAdapter_Events extends BaseAdapter {
    private ArrayList<EventsBL> listData;
    private LayoutInflater layoutInflater;

    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;
    TextView view5;

    public CustomListAdapter_Events(Context aContext, ArrayList<EventsBL> listData) {
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
        convertView = layoutInflater.inflate(R.layout.customlist_adapter_events, null);

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
