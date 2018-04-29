package com.example.jaanfdo.myfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/28/2017.
 */

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<EventsBL> listData;
    private LayoutInflater layoutInflater;

    TextView headlineView;
    TextView reporterNameView;
    TextView reportedDateView;


    public CustomListAdapter(Context aContext, ArrayList<EventsBL> listData) {
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

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
        headlineView = (TextView) convertView.findViewById(R.id.title);
        reporterNameView = (TextView) convertView.findViewById(R.id.reporter);
        reportedDateView = (TextView) convertView.findViewById(R.id.date);

        headlineView.setText(listData.get(position).getEventname());
        reporterNameView.setText("By, " + listData.get(position).getCourse());
        reportedDateView.setText(listData.get(position).getDate());
        return convertView;
    }
}
