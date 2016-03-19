package com.hotmall.library.libutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zhsheng on 2016/3/17.
 */
public class LibUiUtil {
    /**
     * 隐藏键盘
     *
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示键盘
     *
     * @param activity
     * @param view
     */
    public static void showSoftInput(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 判断要打开的Activity是否存在
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean activityExists(Context context, Intent intent) {
        final PackageManager mgr = context.getPackageManager();
        final ResolveInfo info = mgr.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return (info != null);
    }
}
