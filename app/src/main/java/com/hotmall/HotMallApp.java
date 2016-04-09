package com.hotmall;

import android.app.Application;

import com.hotmall.common.UtilCtInit;
import com.hotmall.utils.SharedPre;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;

/**
 * Created by zhsheng on 2016/1/18.
 */
public class HotMallApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPre.init(this, SharedPre.getDefaultSharedPreferencesName());
        UtilCtInit.init(this);
        Iconify.with(new MaterialModule())
                .with(new EntypoModule())
                .with(new MaterialCommunityModule());

    }
}
