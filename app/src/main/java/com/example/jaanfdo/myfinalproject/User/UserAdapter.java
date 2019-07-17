package com.example.jaanfdo.myfinalproject.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.R;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    ArrayList<Users> listData;
    LayoutInflater layoutInflater;

    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;

    public UserAdapter(Context context, ArrayList<Users> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
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
        convertView = layoutInflater.inflate(R.layout.user_array_adapter, null);

        view1 = (TextView) convertView.findViewById(R.id.view1);
        view2 = (TextView) convertView.findViewById(R.id.view2);
        view3 = (TextView) convertView.findViewById(R.id.view3);
        view4 = (TextView) convertView.findViewById(R.id.view4);

        view1.setText("ID " + listData.get(position).getId());
        view2.setText("Username " + listData.get(position).getUsername());
        view3.setText("Password " + listData.get(position).getPassword());
        view4.setText("Email " + listData.get(position).getEmail());

        return convertView;
    }
}
