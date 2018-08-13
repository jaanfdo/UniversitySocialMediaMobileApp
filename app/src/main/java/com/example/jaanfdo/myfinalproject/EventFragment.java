package com.example.jaanfdo.myfinalproject;

import android.content.Context;
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

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 3/11/2017.
 */

public class EventFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_event_fragment, container, false);

        ArrayList<EventsBL> listContact = GetlistContact();
        ListView lv = (ListView)view.findViewById(R.id.eventlistview);
        lv.setAdapter(new ListviewContactAdapter(getActivity(), listContact));

        return view;
    }

    private ArrayList<EventsBL> GetlistContact(){
        ArrayList<EventsBL> contactlist = new ArrayList<EventsBL>();

        EventsBL obj = null;

        obj = new EventsBL();
        obj.setEventname("Topher");
        obj.setDescription("aaaaaaaaaa");
        contactlist.add(obj);

        obj = new EventsBL();
        obj.setEventname("Bewosss");
        obj.setDescription("sssssssssssssssssssss");
        contactlist.add(obj);

        obj = new EventsBL();
        obj.setEventname("Ruwaaa");
        obj.setDescription("ssssssssssdddddddd");
        contactlist.add(obj);

        return contactlist;
    }
}

class ListviewContactAdapter extends BaseAdapter {
    private static ArrayList<EventsBL> listContact;

    private LayoutInflater mInflater;

    public ListviewContactAdapter(Context photosFragment, ArrayList<EventsBL> results){
        listContact = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listContact.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listContact.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.listviewtheme, null);
            holder = new ViewHolder();
            holder.txtname = (TextView) convertView.findViewById(R.id.textView);
            holder.txtphone = (TextView) convertView.findViewById(R.id.textView2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtname.setText(listContact.get(position).getEventname());
        holder.txtphone.setText(listContact.get(position).getDescription());

        return convertView;
    }

    static class ViewHolder{
        TextView txtname, txtphone;
    }
}