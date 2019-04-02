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
import com.wd.hencoder.ColorFilterActivity;
import com.wd.hencoder.DrawViewActivity;
import com.wd.rxn.ObservableNote;

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
        contentView.findViewById(R.id.tv_hencoder).setOnClickListener(this);
        contentView.findViewById(R.id.tv_color_filter).setOnClickListener(this);
        contentView.findViewById(R.id.rxjava_back).setOnClickListener(this);
        contentView.findViewById(R.id.rxjava_btn).setOnClickListener(this);
        contentView.findViewById(R.id.data_struct).setOnClickListener(this);

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
            case R.id.tv_hencoder://自定义view
                startActivity(new Intent(getActivity(), DrawViewActivity.class));
                break;
            case R.id.tv_color_filter://ColorFilter
                startActivity(new Intent(getActivity(), ColorFilterActivity.class));
                break;
            case R.id.rxjava_btn://RxJava操作符
                ObservableNote.rxObservable();
                ObservableNote.rxFlowable();
                ObservableNote.rxSingle();
                ObservableNote.rxMaybe();
                ObservableNote.rxCompletable();
                break;
            case R.id.rxjava_back://RxJava请求事件
                ObservableNote.requestRx();
                break;
            case R.id.data_struct://数据结构
                break;

        }

    }
}
