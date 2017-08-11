package com.wd.emoji.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.wd.eml.utils.EMNavUtil;
import com.wd.eml.utils.EMUtil;
import com.wd.emoji.R;
import com.wd.emoji.adapter.EmFragmentPagerAdapter;
import com.wd.emoji.fragment.FragmentFour;
import com.wd.emoji.fragment.FragmentOne;
import com.wd.emoji.fragment.FragmentThr;
import com.wd.emoji.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wudu
 * time : 2017/8/10
 * EmojiFun Main Page
 */

public class EmojiMainActivity extends BaseActivity {
    private DrawerLayout ac_main_drawlayout;
    private LinearLayout ac_main_layout;
    private ViewPager ac_main_viewpager;
    private BottomNavigationView ac_main_bottom_menu;
    private NavigationView ac_main_nav_layout;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_emoji_main);
        init();
    }

    @Override
    public void setStatusBar() {

    }

    private void init() {
        initView();
        initFragment();
        initToolBar();
        initViewPager();
        initBottomNavView();
    }

    private void initView() {
        ac_main_drawlayout = findView(R.id.ac_main_drawlayout);
        ac_main_layout = findView(R.id.ac_main_layout);
        ac_main_viewpager = findView(R.id.ac_main_viewpager);
        ac_main_bottom_menu = findView(R.id.ac_main_bottom_menu);
        ac_main_nav_layout = findView(R.id.ac_main_nav_layout);

       //EMUtil.setColorForDrawerLayout(this,ac_main_drawlayout,Color.RED,0);
        ac_main_layout.addView(EMUtil.createStatusBarView(this,Color.RED),0);
        ac_main_drawlayout.setFitsSystemWindows(false);
        ac_main_layout.setFitsSystemWindows(false);
        ac_main_layout.setClipToPadding(true);
        ac_main_nav_layout.setFitsSystemWindows(false);
        toolbar.setFitsSystemWindows(false);

        //REMOVE BOTTOM NAVIGATION ANMITION
        EMNavUtil.disShiftingMode(ac_main_bottom_menu);

    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentOne());
        fragmentList.add(new FragmentTwo());
        fragmentList.add(new FragmentThr());
        fragmentList.add(new FragmentFour());
    }

    private void initToolBar() {
        toolbar.setTitle("Emoji");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.RED);
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        ac_main_viewpager.setAdapter(new EmFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
        ac_main_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ac_main_viewpager.setCurrentItem(position);
                ac_main_bottom_menu.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initBottomNavView() {
        BottomNavigationItemView nav1,nav2,nav3,nav4;
         nav1 = (BottomNavigationItemView) ac_main_bottom_menu.findViewById(R.id.menu_bottom_1);
         nav2 = (BottomNavigationItemView) ac_main_bottom_menu.findViewById(R.id.menu_bottom_2);
         nav3 = (BottomNavigationItemView) ac_main_bottom_menu.findViewById(R.id.menu_bottom_3);
         nav4 = (BottomNavigationItemView) ac_main_bottom_menu.findViewById(R.id.menu_bottom_4);

        nav1.setTitle("主题");
        nav2.setTitle("幻想");
        nav3.setTitle("E墙");
        nav4.setTitle("F帖");


        ac_main_bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_bottom_1:
                        ac_main_viewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_bottom_2:
                        ac_main_viewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_bottom_3:
                        ac_main_viewpager.setCurrentItem(2);
                        break;
                    case R.id.menu_bottom_4:
                        ac_main_viewpager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onClick(View view) {
        Log.i(TAG, "---------");
    }
}
