package com.hotmall.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.hotmall.common.ConstantValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class StorageUtil {
    public static final String HIDE_STOR_ROOT = ConstantValue.APP_NAME;
    public static final String PHOTO_STOR_ROOT = HIDE_STOR_ROOT + "/photo/";

    /**
     * 外部缓存目录
     *
     * @return dir like /mnt/sdcard/Android/data/包名/cache
     */
    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    /**
     * 内部缓存目录
     *
     * @return dir like /data/包名/cache
     */
    public static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    /**
     * 缓存目录，文件夹为.，系统不会扫描该文件夹图片
     *
     * @return dir like /mnt/sdcard/Picture/.
     */
    public static File getHidePictureDir() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), HIDE_STOR_ROOT);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 下载目录
     *
     * @return dir like /mnt/sdcard/Download
     */
    public static File getDownloadDir(Context context) {
        File downloadDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (downloadDir == null) {
            downloadDir = context.getFilesDir();
        }
        return downloadDir;
    }

    /**
     * 获取可用的存储空间
     *
     * @return
     */
    public static long getAvailableStorage() {
        String storageDirectory = Environment.getExternalStorageDirectory().toString();
        try {
            StatFs stat = new StatFs(storageDirectory);
            return ((long) stat.getAvailableBlocks() * (long) stat.getBlockSize());
        } catch (RuntimeException ex) {
            return 0;
        }
    }

    /**
     * 判断SdcardExist是否存在
     *
     * @return
     */
    public static boolean isSdcardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 删除指定目录下文件及目录
     */
    public static void deleteFolderFile(File file, boolean deleteThisPath)
            throws IOException {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {// 处理目录
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolderFile(files[i], true);
            }
        }
        if (deleteThisPath) {
            if (!file.isDirectory()) {// 如果是文件，删除
                file.delete();
            } else {// 目录
                if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                    file.delete();
                }
            }
        }
    }

    /**
     * 只删除目录下文件及目录，不删除这个目录！
     */
    public static void deleteFolderFile(File file)
            throws IOException {
        if (file == null) {
            return;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        deleteFolderFile(subFile);
                    } else {
                        subFile.delete();
                    }
                }
            } else {
                file.delete();
            }
        }
    }

    private static File getSystemPhotoDir() {
        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        root = new File(root, "Camera");
        if (!root.exists()) {
            root.mkdirs();
        }
        return root;
    }

    /**
     * 获取保存到系统相册图片目录的路径
     *
     * @param fileName
     * @return
     */
    public static File genSystemPhotoFile(String fileName) {
        return new File(getSystemPhotoDir(), fileName);
    }

    /**
     * 获取保存到系统相册图片目录的路径
     *
     * @return
     */
    public static File genSystemPhotoFile() {
        return genSystemPhotoFile(getPhotoName());
    }

    /**
     * 获取图片缓存目录，系统不会扫描
     *
     * @param fileName
     * @return
     */
    public static File getHidePhotoPath(String fileName) {
        File root = getHidePictureDir();
        File dir = new File(root, PHOTO_STOR_ROOT);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("savePhoto", "create folder " + dir.getPath() + " failed!");
                return null;
            }
        }
        return new File(dir, fileName);
    }


    public static File getPhotoPath() {
        return getHidePhotoPath(getPhotoName());
    }

    private static String getPhotoName() {
        return "TF"
                + System.currentTimeMillis()
                + new Random().nextInt(256)
                + ".jpg";
    }

    public static String savePhotoToAlbum(Bitmap bitmap) {
        File photo = genSystemPhotoFile();
        if (photo != null) {
            try {
                FileOutputStream fos = new FileOutputStream(photo);
                fos.write(bitmap2Bytes(bitmap));
                fos.close();
                return photo.getPath();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    public static String savePhoto(Bitmap bitmap) {
        File photo = genSystemPhotoFile(getPhotoName());
        if (photo != null) {
            try {
                FileOutputStream fos = new FileOutputStream(photo);
                fos.write(bitmap2Bytes(bitmap));
                fos.close();
                return photo.getPath();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "";
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static String getSDPath() {
        File sdDir;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取根目录
            return sdDir.toString();
        } else {
            return "";
        }
    }
}
