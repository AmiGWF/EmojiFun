package com.wd.emoji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.emoji.R;

/**
 * author : wudu
 * time : 2017/8/10
 * Hi,Baby.
 */

public class FragmentFour extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_emoji_four,container,false);
    }
}
