package com.wd.net.services;

import com.wd.eml.utils.EMLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : wudu
 * @Date : 2018/9/5
 * Hi,Baby.
 */

public class BaseObserver<T> implements Observer<T> {
    private ObserverOnNextListener listener;

    public BaseObserver(ObserverOnNextListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        EMLog.d("BaseObserver  onSubscribe");

    }

    @Override
    public void onNext(T t) {
        EMLog.d("BaseObserver  onNext");
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        EMLog.d("BaseObserver  onError");

    }

    @Override
    public void onComplete() {
        EMLog.d("BaseObserver  onComplete");

    }
}
