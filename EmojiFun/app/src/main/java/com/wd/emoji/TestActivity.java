package com.wd.emoji;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.wd.eml.bottomsheetdialog.EMBottomSheetBuilder;

/**
 * author : wudu
 * time : 2017/8/23
 * Hi,Baby.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coor_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button) findViewById(R.id.coor_click);
        button.setOnClickListener(this);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.coor_click){
            Menu menu = new MenuBuilder(this);
            SubMenu menu1 = menu.addSubMenu(Menu.NONE, Menu.NONE, 1, "标题");
            menu1.add(Menu.NONE, Menu.NONE, 1, "菜单1").setIcon(R.drawable.em_ic_camera);
            menu1.add(Menu.NONE, Menu.NONE, 2, "菜单2").setIcon(R.drawable.em_ic_camera);
            menu1.add(Menu.NONE, Menu.NONE, 3, "菜单3");
            menu1.add(Menu.NONE, Menu.NONE, 4, "菜单4");

            View view1 = new EMBottomSheetBuilder(this,coordinatorLayout)
                    .setMode(BottomSheetBuilder.MODE_LIST)
                    .addTitleItem("Title")
                    .setMenus(menu)
                    .setTitleTextColor(R.color.colorAccent)
                    .addItem(1,"item1",R.drawable.em_ic_camera)
                    .addItem(2,"item2",R.drawable.em_ic_camera)
                   .createSheetView();
            behavior = BottomSheetBehavior.from(view1);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);



        }
    }
}
