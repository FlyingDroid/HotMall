package com.hotmall.model.api;

import com.hotmall.model.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhsheng on 2016/3/17.
 */
public interface ApiInterface {
    String HOST = "https://api.heweather.com/x3/";

    @GET("weather")
    Observable<WeatherResponse> mWeatherAPI(@Query("city") String city,
                                            @Query("key") String key);
}
