package com.hotmall.library.libutils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;

/**
 * Created by zhsheng on 2015/9/13.
 */
public class LibFileUtil {

    public static byte[] readBytes(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        return LibIoUtil.readAllBytesAndClose(is);
    }

    public static void writeBytes(File file, byte[] content) throws IOException {
        OutputStream out = new FileOutputStream(file);
        try {
            out.write(content);
        } finally {
            LibIoUtil.safeClose(out);
        }
    }

    public static String readUtf8(File file) throws IOException {
        return readChars(file, "UTF-8");
    }

    public static String readChars(File file, String charset) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream(file), charset);
        return LibIoUtil.readAllCharsAndClose(reader);
    }

    public static void writeUtf8(File file, CharSequence text) throws IOException {
        writeChars(file, "UTF-8", text);
    }

    public static void writeChars(File file, String charset, CharSequence text) throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), charset);
        LibIoUtil.writeAllCharsAndClose(writer, text);
    }

    /**
     * Copies a file to another location.
     */
    public static void copyFile(File from, File to) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(from));
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(to));
            try {
                LibIoUtil.copyAllBytes(in, out);
            } finally {
                LibIoUtil.safeClose(out);
            }
        } finally {
            LibIoUtil.safeClose(in);
        }
    }

    /**
     * Copies a file to another location.
     */
    public static void copyFile(String fromFilename, String toFilename) throws IOException {
        copyFile(new File(fromFilename), new File(toFilename));
    }

    /**
     * To read an object in a quick & dirty way. Prepare to handle failures when object serialization changes!
     */
    public static Object readObject(File file) throws IOException,
            ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(fileIn));
        try {
            return in.readObject();
        } finally {
            LibIoUtil.safeClose(in);
        }
    }

    /**
     * To store an object in a quick & dirty way.
     */
    public static void writeObject(File file, Object object) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fileOut));
        try {
            out.writeObject(object);
            out.flush();
            // Force sync
            fileOut.getFD().sync();
        } finally {
            LibIoUtil.safeClose(out);
        }
    }

    /**
     * @return MD5 digest (32 characters).
     */
    public static String getMd5(File file) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        try {
            return LibIoUtil.getMd5(in);
        } finally {
            LibIoUtil.safeClose(in);
        }
    }

    /**
     * @return SHA-1 digest (40 characters).
     */
    public static String getSha1(File file) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        try {
            return LibIoUtil.getSha1(in);
        } finally {
            LibIoUtil.safeClose(in);
        }
    }

    public static void copyAssetsFileToSD(Context context, String assetsFileName, String strOutFileName) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            myOutput = new FileOutputStream(strOutFileName);
            myInput = context.getAssets().open(assetsFileName);
            byte[] buffer = new byte[1024];
            int length = myInput.read(buffer);
            while (length > 0) {
                myOutput.write(buffer, 0, length);
                length = myInput.read(buffer);
            }

            myOutput.flush();
            myInput.close();
            myOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LibIoUtil.safeClose(myInput);
            LibIoUtil.safeClose(myOutput);
        }
    }

    public String getFromAssets(Context context, String fileName) {
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    public static String getFromRaw(Context context, int rawRsd) {
        String line = "";
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().openRawResource(rawRsd));
            BufferedReader bufReader = new BufferedReader(inputReader);
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }


    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size = size + getFolderSize(fileList[i]);
            } else {
                size = size + fileList[i].length();
            }
        }
        return size;
    }

    // 转换文件大小
    public static String FormatFileSize(long fileS) {
        if (fileS == 0) {
            return "0K";
        }
        DecimalFormat df = new DecimalFormat("#.0");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
