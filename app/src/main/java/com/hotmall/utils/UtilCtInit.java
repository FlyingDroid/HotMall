package com.hotmall.utils;

import android.content.Context;

public class UtilCtInit {
    private static Context mContext;

    public static void init(Context mContext) {
        UtilCtInit.mContext = mContext;
    }

    public static Context getContext() {
        return mContext;
    }

}
