package com.example.jaanfdo.myfinalproject.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.R;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/28/2017.
 */

public class CustomListAdapter_News extends BaseAdapter {
    private ArrayList<NewsBL> listData;
    private LayoutInflater layoutInflater;

    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;


    public CustomListAdapter_News(Context aContext, ArrayList<NewsBL> listData) {
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
        convertView = layoutInflater.inflate(R.layout.customlist_adapter_news, null);

        view1 = (TextView) convertView.findViewById(R.id.view1);
        view2 = (TextView) convertView.findViewById(R.id.view2);
        view3 = (TextView) convertView.findViewById(R.id.view3);
        view4 = (TextView) convertView.findViewById(R.id.view4);


        view1.setText("ID " + listData.get(position).getId());
        view2.setText(listData.get(position).getNews());
        view3.setText("User " + listData.get(position).getOwner());
        view4.setText("Event Date " + listData.get(position).getDate() + " " + listData.get(position).getTime());


        return convertView;
    }
}
