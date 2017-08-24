package com.wd.emoji;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wd.eml.bottomsheetdialog.EMBottomSheetBuilder;
import com.wd.eml.bottomsheetdialog.EMBottomSheetDialog;
import com.wd.eml.bottomsheetdialog.interfaces.BottomSheetItemClickListener;
import com.wd.eml.utils.EMLog;

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
            EMBottomSheetDialog dialog = new EMBottomSheetBuilder(this)
                    .setMode(EMBottomSheetBuilder.MODE_LIST)
                    .addTitleItem("Griddle",getResources().getColor(R.color.colorAccent),getResources().getColor(R.color.card_blue_bg),R.drawable.em_ic_camera)
                    .addItem(0,"条目一 ")
                    .addItem(3,"还是信赖的",R.drawable.em_ic_camera)
                    .addItem(1,"222222")
                    .addItem(2,"222222")
                    .addDividerItem(getResources().getColor(R.color.design_textinput_error_color_light))
                    .addItem(3,"222222")
                    .setBottomSheetItemClickListener(new BottomSheetItemClickListener() {
                        @Override
                        public void onBottomSheetItemClick(MenuItem item) {
                            EMLog.d("点击事件 ： "+item.getItemId()+",   "+item.getMenuInfo());
                        }
                    })
                    .createSheetDialog();
            dialog.show();




//            View menuDialog = new BottomSheetBuilder(this,coordinatorLayout)
//                    .setMode(BottomSheetBuilder.MODE_GRID)
//                    .addItem(0,"111111111",R.drawable.navigation_empty_icon)
//                    .addItem(1,"2222222222222222",R.drawable.navigation_empty_icon)
//                    .addDividerItem()
//                    .expandOnStart(true)
//                   .createView();
            //behavior = BottomSheetBehavior.from(coordinatorLayout);
        }
    }
}
