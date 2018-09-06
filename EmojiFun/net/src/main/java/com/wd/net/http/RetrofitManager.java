package com.wd.net.http;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author : wudu
 * @Date : 2018/8/28
 * Hi,Baby.
 */

public class RetrofitManager {
    private static final String BASEURL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_WRITE_OUT = 10;

    private Retrofit retrofit;
    private OkHttpClient client;

    private RetrofitManager() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type","applicaton/json").build();
                return chain.proceed(build);
            }
        };

        client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_WRITE_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_READ_WRITE_OUT, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .retryOnConnectionFailure(true)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    private static class SingletonHandler {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return SingletonHandler.INSTANCE;
    }

    /**
     * 获取对应的service
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        if (retrofit == null) {
            throw new IllegalArgumentException("retrofit is null,please init at first...");
        }
        return retrofit.create(service);
    }


    /**
     * 设置订阅和线程
     *
     * @param observable
     * @param disposableObserver
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> observable, DisposableObserver<T> disposableObserver) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(0)
                .subscribe(disposableObserver);
    }

}
