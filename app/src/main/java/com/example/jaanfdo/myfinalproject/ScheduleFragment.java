package com.example.jaanfdo.myfinalproject;

import android.content.Context;
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

import java.util.ArrayList;


/**
 * Created by jaanfdo on 3/11/2017.
 */

public class ScheduleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_schedule_fragment, container, false);

        ArrayList<ScheduleBL> listContact = GetlistContact();
        ListView lv = (ListView)view.findViewById(R.id.schedulelistview);
        lv.setAdapter(new ListviewContactAdapter3(getActivity(), listContact));

        return view;
    }
    private ArrayList<ScheduleBL> GetlistContact(){
        ArrayList<ScheduleBL> contactlist = new ArrayList<ScheduleBL>();

        ScheduleBL obj = null;

        obj = new ScheduleBL();
        obj.setLecname("Topher");
        obj.setCourse("aaaaaaaaaa");
        contactlist.add(obj);

        obj = new ScheduleBL();
        obj.setLecname("Bewosss");
        obj.setCourse("sssssssssssssssssssss");
        contactlist.add(obj);

        obj = new ScheduleBL();
        obj.setLecname("Ruwaaa");
        obj.setCourse("ssssssssssdddddddd");
        contactlist.add(obj);

        return contactlist;
    }
}

class ListviewContactAdapter3 extends BaseAdapter {
    private static ArrayList<ScheduleBL> listContact;

    private LayoutInflater mInflater;

    public ListviewContactAdapter3(Context photosFragment, ArrayList<ScheduleBL> results){
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

        holder.txtname.setText(listContact.get(position).getLecname());
        holder.txtphone.setText(listContact.get(position).getSubject());

        return convertView;
    }

    static class ViewHolder{
        TextView txtname, txtphone;
    }
}
