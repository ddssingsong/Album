package com.trustmobi.basiclib.utils;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class Util {

    //检查权限是否可用
    public static boolean checkPermissionWrite(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            boolean var1 = false;
            if (context != null) {
                var1 = context.getApplicationContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED;
            }
            return var1;
        }
    }

    //是否挂载sd
    public static boolean checkMounted() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //对比文件
    public boolean isFileSame(File file, File file1) {
        return file.length() == file1.length() && file.lastModified() == file1.lastModified();
    }

}
