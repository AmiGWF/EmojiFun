package com.wd.emoji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author : wudu
 * time : 2017/8/10
 * ViewPager adapter
 */

public class EmFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public EmFragmentPagerAdapter(List<Fragment> fragmentList1, FragmentManager fm) {
        super(fm);
        this.fragmentList = fragmentList1;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
