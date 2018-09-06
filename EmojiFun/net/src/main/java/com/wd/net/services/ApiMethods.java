package com.wd.net.services;

import com.wd.net.http.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : wudu
 * @Date : 2018/9/5
 * Hi,Baby.
 */

public class ApiMethods {
    public static <T> void apiSubscribe(Observable<T> observable , Observer<T> observer){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static  void getTopMovie(Observer observer,int start,int count){
        apiSubscribe(RetrofitManager.getInstance().create(ApiService.class).getTopMovies(start,count),observer);
    }
}
