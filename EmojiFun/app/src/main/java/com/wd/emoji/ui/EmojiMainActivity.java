package com.wd.emoji.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.eml.bottomnavigationview.EMNavUtil;
import com.wd.eml.bottomsheetdialog.EMBottomSheetDialog;
import com.wd.eml.palette.EMPaletteUtil;
import com.wd.eml.utils.EMLog;
import com.wd.emoji.R;
import com.wd.emoji.adapter.EmFragmentPagerAdapter;
import com.wd.emoji.fragment.FragmentFour;
import com.wd.emoji.fragment.FragmentOne;
import com.wd.emoji.fragment.FragmentThr;
import com.wd.emoji.fragment.FragmentTwo;
import com.wd.emojiui.base.BaseActivity;
import com.wd.emojiui.pages.EmLoginActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author : wudu
 * time : 2017/8/10
 * EmojiFun Main Page
 */

public class EmojiMainActivity extends BaseActivity implements View.OnClickListener {
    public Toolbar toolbar;
    private DrawerLayout ac_main_drawlayout;
    private LinearLayout ac_main_layout;
    private ViewPager ac_main_viewpager;
    private BottomNavigationView ac_main_bottom_menu;
    private NavigationView ac_main_nav_layout;
    //HEADER BACKGROUND
    private CoordinatorLayout em_header_top_bg;
    private CircleImageView em_header_icon;
    private TextView em_header_name, em_header_tips;

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
        initLeftNavView();
        initBottomSheetDialog();
        initClick();
    }

    @SuppressLint("ResourceType")
    private void initView() {
        toolbar = findViewById(R.id.ac_main_toolbar);
        ac_main_drawlayout = findView(R.id.ac_main_drawlayout);
        ac_main_layout = findView(R.id.ac_main_layout);
        ac_main_viewpager = findView(R.id.ac_main_viewpager);
        ac_main_bottom_menu = findView(R.id.ac_main_bottom_menu);
        ac_main_nav_layout = findView(R.id.ac_main_nav_layout);
        //HEADER:此处必须使用getHeaderView先获取头部布局，否则会找不到相关控件
        View headerView = ac_main_nav_layout.getHeaderView(0);
        em_header_top_bg = headerView.findViewById(R.id.em_header_top_bg);
        em_header_icon = headerView.findViewById(R.id.em_header_icon);
        em_header_name = headerView.findViewById(R.id.em_header_name);
        em_header_tips = headerView.findViewById(R.id.em_header_tips);

    }

    private void initClick() {
        //CLICK
        em_header_icon.setOnClickListener(this);
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
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.em_bottom_menu_pressed));
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
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
                    default:
                        break;
                }
                return true;
            }
        });

        //SET BOTTOM MENU ITEM COLOR
        EMNavUtil.setBottomNavMenuColor(this, ac_main_bottom_menu, R.color.em_bottom_menu_normal, R.color
                .em_bottom_menu_pressed);

        //REMOVE BOTTOM NAVIGATION ANMITION
        EMNavUtil.disShiftingMode(ac_main_bottom_menu);

    }

    private void initLeftNavView() {
        ac_main_nav_layout.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_nav_1:
                        Log.d(TAG, "ac_main_nav_layout : " + EmojiMainActivity.class.getSimpleName());
                        break;
                    case R.id.menu_nav_2:
                        break;
                    case R.id.menu_nav_3:
                        break;
                    case R.id.menu_nav_4:
                        break;
                    case R.id.menu_nav_5:
                        break;
                    case R.id.menu_nav_6:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //SET LEFT MENU ITEM COLOR
        EMNavUtil.setNavMenuColor(this, ac_main_nav_layout, R.color.em_bottom_menu_normal, R.color
                .em_bottom_menu_pressed);

        //SET LEFT MENU BACKGROUND
        EMPaletteUtil.getIntance().init(getResources(), R.drawable.bg3, new EMPaletteUtil.PaletteCallBack() {
            @Override
            public void onCallBack(Palette palette) {
            }

            @Override
            public void onCallBack(int rgb, int titleColor) {
                ac_main_nav_layout.setBackgroundColor(rgb);
            }
        });
    }


    EMBottomSheetDialog dialog;

    private void initBottomSheetDialog() {
//       dialog = new EMBottomSheetBuilder(this)
//               .setMode(EMBottomSheetBuilder.MODE_LIST)
//               .addTitleItem("title")
//               .addItem(0,"item11111")
//               .addItem(1,"item  222",getResources().getColor(R.color.primary_text_color))
//               .setExpandOnStart(true)
//               .setBottomSheetItemClickListener(new BottomSheetItemClickListener() {
//                   @Override
//                   public void onBottomSheetItemClick(MenuItem item) {
//                       EMLog.d("menu iten clicl  ooo : "+item.getItemId());
//                   }
//               })
//               .createSheetDialog();

    }

    @Override
    public void onClick(View view) {
        EMLog.i("--------");
        int id = view.getId();
        switch (id) {
            case R.id.em_header_icon:
                gotoLogin();
                break;
        }
    }

    private void gotoLogin() {
        startActivity(new Intent(EmojiMainActivity.this, EmLoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
