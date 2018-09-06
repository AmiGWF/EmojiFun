package com.wd.net.services;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author : wudu
 * @Date : 2018/8/28
 * 对外部提供的接口
 */

public interface HttpService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String, String> map);

}
