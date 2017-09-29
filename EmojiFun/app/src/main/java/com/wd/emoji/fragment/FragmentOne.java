package com.wd.emoji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.ac.BezierActivity;
import com.wd.eml.ac.SheetDialogActivity;
import com.wd.emoji.R;

/**
 * author : wudu
 * time : 2017/8/10
 * Hi,Baby.
 */

public class FragmentOne extends BaseFragment implements View.OnClickListener {
    private View contentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fg_emoji_one, container, false);
        initView();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView() {
        contentView.findViewById(R.id.tv_sned_heart).setOnClickListener(this);
        contentView.findViewById(R.id.tv_sheet_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_sned_heart://点赞送爱心
                startActivity(new Intent(getActivity(), BezierActivity.class));
                break;
            case R.id.tv_sheet_dialog://底部菜单
                startActivity(new Intent(getActivity(), SheetDialogActivity.class));
                break;
        }

    }
}
