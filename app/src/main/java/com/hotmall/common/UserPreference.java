package com.hotmall.common;

import android.content.Context;

import net.grandcentrix.tray.TrayPreferences;
import net.grandcentrix.tray.core.TrayStorage;

/**
 * Created by zhsheng on 2016/1/23.
 */
public class UserPreference extends TrayPreferences {
    public static final String USER_ACCOUNT = "userAccount";
    public static final String USER_PASSWORD = "userPassword";

    public UserPreference(Context context, String module, int version, TrayStorage.Type type) {
        super(context, module, version, type);
    }

    public UserPreference(Context context, String module, int version) {
        super(context, module, version);
    }

    public UserPreference(Context context) {
        super(context, "userModule", 1);
    }
}
