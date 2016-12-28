package com.example.youngmi.mycalendar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final static String TAG = MainActivity.class.getName();

    private Calendar mCalendar;
    private List<TextView> mDayTextViewList;
    private TextView mTitle;
    private ViewPager mCalendarPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCalendarPager = (ViewPager) findViewById(R.id.pager_calendar);
        mCalendarPager.setAdapter(new CalendarPagerAdapter(this));


        mTitle = (TextView) findViewById(R.id.text_year_and_month);

//        mDayTextViewList = new ArrayList<>();
//        Resources res = getResources();
//        int resID;
//        TextView tv;
//
//        for(int r=0; r<6; r++) {
//            for(int c=0; c<7; c++) {
//                resID = res.getIdentifier("calendar_" + r + "_" + c, "id", "com.example.youngmi.mycalendar");
//                if(resID != 0) {
//                    tv = (TextView) findViewById(resID);
////                    tv.setText(r + "," + c);
//                    mDayTextViewList.add(tv);
//                }
//            }
//        }
//
//
//        mCalendar = Calendar.getInstance();
//        drawCalendar();

        Button beforeButton = (Button) findViewById(R.id.btn_before_month);
        beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendar.add(Calendar.MONTH, -1);
                drawCalendar();
            }
        });

        Button nextButton = (Button) findViewById(R.id.btn_next_month);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.add(Calendar.MONTH, 1);
                drawCalendar();
            }
        });
    }

    private void drawCalendar() {
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH)+1;
        mTitle.setText(year + "년 " + month + "월");

        int today = mCalendar.get(Calendar.DAY_OF_MONTH);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        int startPoint = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        int maxMonth = mCalendar.getActualMaximum(Calendar.DATE);
        TextView tv;
        int index = 0;

        for(;index < startPoint; index++) {
            tv = mDayTextViewList.get(index);
            tv.setText("");
        }

        for(; index < startPoint+maxMonth; index++) {
            tv = mDayTextViewList.get(index);
            tv.setText(String.valueOf(index-startPoint+1));
        }

        for(;index < mDayTextViewList.size(); index++) {
            tv = mDayTextViewList.get(index);
            tv.setText("");
        }

        mCalendar.set(Calendar.DAY_OF_MONTH, today);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
