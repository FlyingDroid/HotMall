package com.hotmall.model.api;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.hotmall.BuildConfig;
import com.hotmall.utils.SessionUtil;
import com.hotmall.utils.StorageUtil;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class API {


    private static final String TAG = API.class.getSimpleName();
    private static ApiInterface apiService;
    private static ApiWeather apiWeather;

    public static void init() {
        OkHttpClient okHttpClient = initReqClient();
        Executor executor = Executors.newCachedThreadPool();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        Retrofit retrofit = retrofitBuilder
                .baseUrl(ApiInterface.HOST)
                .client(okHttpClient)
                .callbackExecutor(executor)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ToStringConverterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiInterface.class);

        retrofit = retrofitBuilder
                .baseUrl(ApiWeather.HOST)
                .client(okHttpClient)
                .callbackExecutor(executor)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ToStringConverterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
        apiWeather = retrofit.create(ApiWeather.class);

    }

    private static OkHttpClient initReqClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.cache(new Cache(StorageUtil.getCacheDir(), RxCache.CACHE_MAX_SIZE));
        httpClientBuilder.networkInterceptors().add(
                chain -> {
                    Request.Builder req = chain.request().newBuilder();
                    for (Map.Entry<String, String> entry : SessionUtil.getHttpHeaders().entrySet()) {
                        req.addHeader(entry.getKey(), entry.getValue());
                        if (BuildConfig.LOG_DEBUG) {
                            Log.d("HTTP-HEADER", "key :" + entry.getKey() + ", value : " + entry.getValue());
                        }
                    }

                    Response response = chain.proceed(req.build());
                    boolean gzip = response.headers().get("Data-Type") != null && response.headers().get("Data-Type").equals("gzip");
                    if (gzip) {
                        return response.newBuilder().removeHeader("Data-Type").addHeader("Content-Encoding", "gzip").build();
                    } else {
                        return response.newBuilder().build();
                    }
                });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.DEFINE);
            httpClientBuilder.interceptors().add(interceptor);
        }
        return httpClientBuilder.build();
    }


    public static ApiInterface getApiService() {
        if (apiService != null) return apiService;
        init();
        return getApiService();
    }

    public static ApiWeather getWeatherApiService() {
        if (apiWeather != null) return apiWeather;
        init();
        return getWeatherApiService();
    }


    public static void disposeFailureInfo(Throwable t, Context context, View view) {
        if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
                t.toString().contains("UnknownHostException")) {
            Snackbar.make(view, "木办法，网络不好呀", Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
        }
        Log.w(TAG, t.toString());
    }
}
