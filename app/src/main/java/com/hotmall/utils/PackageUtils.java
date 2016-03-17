package com.hotmall.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

public class PackageUtils {

    /**
     * App installation location settings values, same to
     */
    public static final int APP_INSTALL_AUTO = 0;
    public static final int APP_INSTALL_INTERNAL = 1;
    public static final int APP_INSTALL_EXTERNAL = 2;

    /**
     * Installation return code<br/>
     * install success.
     */
    public static final int INSTALL_SUCCEEDED = 1;
    /**
     * Installation return code<br/>
     * the package is already installed.
     */
    public static final int INSTALL_FAILED_ALREADY_EXISTS = -1;

    /**
     * Installation return code<br/>
     * the package archive file is invalid.
     */
    public static final int INSTALL_FAILED_INVALID_APK = -2;

    /**
     * Installation return code<br/>
     * the URI passed in is invalid.
     */
    public static final int INSTALL_FAILED_INVALID_URI = -3;

    /**
     * install according conditions
     * <ul>
     * <li>else see {@link #installNormal(Context, String)}</li>
     * </ul>
     */
    public static final int install(Context context, String filePath) {
        return installNormal(context, filePath) ? INSTALL_SUCCEEDED
                : INSTALL_FAILED_INVALID_URI;
    }

    /**
     * install package normal by system intent
     *
     * @param filePath file path of package
     * @return whether apk exist
     */
    public static boolean installNormal(Context context, String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file == null
                || !file.exists()
                || !file.isFile()
                || file.length() <= 0) {
            return false;
        }

        i.setDataAndType(Uri.parse("file://" + filePath),
                "application/vnd.android.package-archive");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        return true;
    }

    /**
     * uninstall package normal by system intent
     *
     * @param packageName package name of app
     * @return whether package name is empty
     */
    public static boolean uninstallNormal(Context context, String packageName) {
        if (packageName == null || packageName.length() == 0) {
            return false;
        }

        Intent i = new Intent(Intent.ACTION_DELETE, Uri.parse(
                new StringBuilder(32).append("package:")
                        .append(packageName)
                        .toString()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        return true;
    }

    /**
     * 获取apk信息
     *
     * @param context
     * @param absPath
     * @return pachageInfo
     */
    public static PackageInfo getApkInfo(Context context, String absPath) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.getPackageArchiveInfo(absPath, 0);
    }
}
