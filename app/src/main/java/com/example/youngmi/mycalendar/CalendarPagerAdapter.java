package com.example.youngmi.mycalendar;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by YOUNGMI on 2016-12-28.
 */

public class CalendarPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int mStartIndex;

    public CalendarPagerAdapter(Context context, int startIndex) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mStartIndex = startIndex;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup pager, int position) {
        int monthCount = position - mStartIndex;

        View v = mInflater.inflate(R.layout.item_pager_calendar, null);

        List<TextView> tvList = new ArrayList<>();
        Resources res = mContext.getResources();
        int resID;
        TextView tv;

        for(int r=0; r<6; r++) {
            for(int c=0; c<7; c++) {
                resID = res.getIdentifier("calendar_" + r + "_" + c, "id", "com.example.youngmi.mycalendar");
                if(resID != 0) {
                    tv = (TextView) v.findViewById(resID);
                    tvList.add(tv);
                }
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int startPoint = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int maxMonth = calendar.getActualMaximum(Calendar.DATE);
        int index = 0;

        for(;index < startPoint; index++) {
            tv = tvList.get(index);
            tv.setText("");
        }

        for(; index < startPoint+maxMonth; index++) {
            tv = tvList.get(index);
            tv.setText(String.valueOf(index-startPoint+1));
        }

        for(;index < tvList.size(); index++) {
            tv = tvList.get(index);
            tv.setText("");
        }

        if(monthCount == 0) {
            int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            tv = tvList.get(startPoint + today);
            tv.setBackgroundColor(0x0000);
        }

        ((ViewPager)pager).addView(v, 0);

        return v;
    }



    @Override
    public void destroyItem(ViewGroup pager, int position, Object view) {
        ((ViewPager)pager).removeView((View)view);
    }
}
