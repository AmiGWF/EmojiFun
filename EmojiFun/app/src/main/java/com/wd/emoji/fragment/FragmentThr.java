package com.wd.emoji.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.eml.utils.EMLog;
import com.wd.emoji.R;
import com.wd.net.response.TopMovies;
import com.wd.net.services.ApiMethods;
import com.wd.net.services.BaseObserver;
import com.wd.net.services.ObserverOnNextListener;


/**
 * author : wudu
 * time : 2017/8/10
 * Hi,Baby.
 */

public class FragmentThr extends BaseFragment implements View.OnClickListener {
    private View fragmentView;
    private TextView re_get;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fg_emoji_thr, container, false);
        initView();
        return fragmentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        re_get = fragmentView.findViewById(R.id.re_get);
        re_get.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.re_get:
                reuqestGet();
                break;
            case R.id.re_post:
                break;
            default:
                break;
        }
    }

    private void reuqestGet() {
        ObserverOnNextListener<TopMovies> listener = new ObserverOnNextListener<TopMovies>() {
            @Override
            public void onNext(TopMovies topMovies) {
                EMLog.d("title ; "+topMovies.getTitle());
                EMLog.d("title ; "+topMovies.getSubjects().get(0).getTitle());
            }
        };

        ApiMethods.getTopMovie(new BaseObserver<TopMovies>(listener),0,10);
    }
}
