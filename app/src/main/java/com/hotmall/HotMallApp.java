package com.hotmall;

import android.app.Application;
import android.content.Context;

import com.hotmall.utils.UtilCtInit;

/**
 * Created by zhsheng on 2016/1/18.
 */
public class HotMallApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UtilCtInit.init(this);
    }

    public static HotMallApp from(Context context) {
        return (HotMallApp) context.getApplicationContext();
    }
}
