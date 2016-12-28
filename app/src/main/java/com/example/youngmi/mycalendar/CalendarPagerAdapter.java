package com.example.youngmi.mycalendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by YOUNGMI on 2016-12-28.
 */

public class CalendarPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    public CalendarPagerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup pager, int position) {
        View v = null;
        v = mInflater.inflate(R.layout.item_pager_calendar, null);

        ((ViewPager)pager).addView(v, 0);

        return v;
    }



    @Override
    public void destroyItem(ViewGroup pager, int position, Object view) {
        ((ViewPager)pager).removeView((View)view);
    }
}
