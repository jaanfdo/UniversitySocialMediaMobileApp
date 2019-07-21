package com.example.jaanfdo.myfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainForm extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Event"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        myTabAdapter adapter = new myTabAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        /*
        ArrayAdapter arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.Planets, android.R.layout.simple_list_item_1);
        ListView listViewItems2 = (ListView) findViewById(R.id.schedulelistview);
        listViewItems2.setAdapter(arrayAdapter2);

        ArrayAdapter arrayAdapter3 = ArrayAdapter.createFromResource(this, R.array.teams, android.R.layout.simple_list_item_1);
        ListView listViewItems3 = (ListView) findViewById(R.id.newslistview);
        listViewItems3.setAdapter(arrayAdapter3);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.info) {
            Intent intent1 = new Intent(this,Profile.class);
            startActivity(intent1);
        }
        else if (id == R.id.login) {
            Intent intent1 = new Intent(this,Login.class);
            startActivity(intent1);
        }
        else if (id == R.id.signup) {
            Intent intent1 = new Intent(this,SignUp.class);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        //FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.news) {
            //fragmentManager.beginTransaction().replace(R.id.content_navigator_drawer , new SignUp()).commit();
            Intent intent1 = new Intent(this,NewsFeeds.class);
            startActivity(intent1);

        } else if (id == R.id.event) {
            Intent intent1 = new Intent(this,Events.class);
            startActivity(intent1);

        } else if (id == R.id.classschedule) {
            Intent intent1 = new Intent(this,ClassSchedule.class);
            startActivity(intent1);

        } else if (id == R.id.teacherschedule) {
            Intent intent1 = new Intent(this,TeacherAppointment.class);
            startActivity(intent1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
