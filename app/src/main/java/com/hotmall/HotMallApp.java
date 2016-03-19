package com.hotmall;

import android.app.Application;

import com.hotmall.common.UtilCtInit;

/**
 * Created by zhsheng on 2016/1/18.
 */
public class HotMallApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UtilCtInit.init(this);
    }
}
