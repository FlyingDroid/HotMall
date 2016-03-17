package com.hotmall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.ViewConfiguration;

import java.io.File;
import java.util.Locale;

public class DeviceUtil {
    public static String getPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    public static String getLanguageInfo(Context context) {
        String language = context
                .getResources()
                .getConfiguration().locale.getLanguage();
        if (language.toLowerCase(Locale.getDefault()).contains("zh")) {
            return "CN";
        } else if (language.toLowerCase(Locale.getDefault()).contains("tw")) {
            return "TW";
        } else {
            return "US";
        }
    }

    public static int getStatusBarHeight(Activity context) {
        Rect rect = new Rect();
        context.getWindow()
                .getDecorView()
                .getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getActionBarHeight(Context context) {
        final TypedArray ta = context.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        return (int) ta.getDimension(0, 0);
    }

    /**
     * 屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Activity context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        context.getWindowManager()
                .getDefaultDisplay()
                .getMetrics(mDisplayMetrics);
        return mDisplayMetrics.widthPixels;
    }

    /**
     * 获取高度, 包括状态栏,但是不包括底部虚拟按键
     */
    public static int getScreenHeight(Activity context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        context.getWindowManager()
                .getDefaultDisplay()
                .getMetrics(mDisplayMetrics);
        return mDisplayMetrics.heightPixels;
    }

    /**
     * 获取导航栏高度
     */
    public static int getNavigationBarHeight(Activity context) {
        if (!checkDeviceHasNavigationBar(context)) {
            return 0;
        }
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    /**
     * 判断有无导航栏
     */
    public static boolean checkDeviceHasNavigationBar(Context activity) {
        if (Build.FINGERPRINT.startsWith("generic")) {
            return false;
        }
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        return hasMenuKey && hasBackKey;
    }

    /**
     * 是否为横屏
     *
     * @param context
     * @return
     */
    public static boolean isLandscape(Context context) {
        Configuration configuration = context.getResources().getConfiguration(); //获取设置的配置信息
        int ori = configuration.orientation; //获取屏幕方向
        return ori == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 屏幕高宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Activity context) {
        int w = getScreenWidth(context);
        int h = getScreenHeight(context);
        return h / w;
    }

    /**
     * 设置摄像头的旋转方向
     *
     * @param activity
     * @param cameraId
     * @param camera
     */
    public static void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    /**
     * 扫描、刷新相册
     */
    public static void scanCamera(String filePath, Context context) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

}