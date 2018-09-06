package com.wd.net.services;

import com.wd.net.response.TopMovies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author : wudu
 * @Date : 2018/9/4
 * Hi,Baby.
 */

public interface ApiService {
    @GET("top250")
     Observable<TopMovies> getTopMovies(@Query("start") int start, @Query("count") int ocunt);
}
