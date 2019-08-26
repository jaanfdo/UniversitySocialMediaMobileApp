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
import android.widget.ListView;
import android.widget.TextView;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;
import com.example.jaanfdo.myfinalproject.CustomAdapter.CustomListAdapter_News;
import com.example.jaanfdo.myfinalproject.Database.NewsDB;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 3/11/2017.
 */

public class NewsFragment extends Fragment {
    NewsDB db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news_fragment, container, false);
        db = new NewsDB(this.getContext());

        ArrayList detail = getListData();
        if(detail.size() > 0) {
            ListView lv = (ListView) view.findViewById(R.id.newslistview);
            lv.setAdapter(new ListviewContactAdapter2(getActivity(), detail));
        }

        return view;
    }

    public ArrayList getListData() {
        ArrayList<NewsBL> results = new ArrayList<NewsBL>();
        Cursor cursor = db.DisplayAll();

        if (cursor.moveToFirst()) {
            do {
                NewsBL newsData = new NewsBL();
                newsData.setId(cursor.getString(0));
                newsData.setNews(cursor.getString(1));
                newsData.setDate(cursor.getString(2));
                newsData.setTime(cursor.getString(3));
                newsData.setOwner(cursor.getString(4));
                results.add(newsData);
            } while (cursor.moveToNext());
        }

        return results;
    }
}

class ListviewContactAdapter2 extends BaseAdapter {
    private static ArrayList<NewsBL> listContact;

    private LayoutInflater mInflater;
    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;

    public ListviewContactAdapter2(Context photosFragment, ArrayList<NewsBL> results){
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
        //ViewHolder holder;
        //if(convertView == null){
            convertView = mInflater.inflate(R.layout.customlist_adapter_news, null);
//            holder = new ViewHolder();
//            holder.txtname = (TextView) convertView.findViewById(R.id.textView);
//            holder.txtphone = (TextView) convertView.findViewById(R.id.textView2);

            view1 = (TextView) convertView.findViewById(R.id.view1);
            view2 = (TextView) convertView.findViewById(R.id.view2);
            view3 = (TextView) convertView.findViewById(R.id.view3);
            view4 = (TextView) convertView.findViewById(R.id.view4);

//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }

        view1.setText("ID " + listContact.get(position).getId());
        view2.setText(listContact.get(position).getNews());
        view3.setText("User " + listContact.get(position).getOwner());
        view4.setText("Event Date " + listContact.get(position).getDate() + " " + listContact.get(position).getTime());

        return convertView;
    }

//    static class ViewHolder{
//        TextView txtname, txtphone;
//    }
}



























//ArrayList<NewsBL> listContact = GetlistContact();
//ListView lv = (ListView)view.findViewById(R.id.newslistview);
//lv.setAdapter(new ListviewContactAdapter2(getActivity(), listContact));

//    private ArrayList<NewsBL> GetlistContact(){
//        ArrayList<NewsBL> contactlist = new ArrayList<NewsBL>();
//
//        NewsBL obj = null;
//
//        obj = new NewsBL();
//        obj.setNews("Topher");
//        obj.setOwner("aaaa aaaaaa");
//        contactlist.add(obj);
//
//        obj = new NewsBL();
//        obj.setNews("Bewo sss");
//        obj.setOwner("ssss sssss ssssss ssssss");
//        contactlist.add(obj);
//
//        obj = new NewsBL();
//        obj.setNews("Ruwaaa");
//        obj.setOwner("ssssss ssssddd ddddd");
//        contactlist.add(obj);
//
//        return contactlist;
//    }