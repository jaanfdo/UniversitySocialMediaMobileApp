package com.example.jaanfdo.myfinalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by jaanfdo on 3/10/2017.
 */

public class myTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public myTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0){
            fragment = new NewsFragment();
        }if(position == 1){
            fragment = new EventFragment();
        }if(position == 2){
            fragment = new ScheduleFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
