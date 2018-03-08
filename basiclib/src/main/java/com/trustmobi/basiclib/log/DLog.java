package com.trustmobi.basiclib.log;


import android.content.Context;
import android.widget.TextView;

public class DLog {
    private static boolean open = true;
    private static DLogClient logClient = null;

    public DLog() {
    }


    public static void i(String var0, String var1) {
        if (logClient != null) {
            logClient.i(var0, var1);
        }
    }

    public static void i(String var0, String var1, boolean var2) {
        i(var0, var1);
        if (logClient != null && open && var2) {
            logClient.showLog(var1);
        }

    }

    public static void e(String var0, String var1) {
        if (logClient != null) {
            logClient.e(var0, var1);
        }
    }

    public static void e(String var0, String var1, boolean var2) {
        e(var0, var1);
        if (logClient != null && open && var2) {
            logClient.showLog(var1);
        }

    }

    public static void w(String var0, String var1) {
        if (logClient != null) {
            logClient.w(var0, var1);
        }
    }

    public static void w(String var0, String var1, boolean var2) {
        w(var0, var1);
        if (logClient != null && open && var2) {
            logClient.showLog(var1);
        }

    }

    public static void d(String var0, String var1) {
        if (logClient != null) {
            logClient.d(var0, var1);
        }
    }

    public static void d(String var0, String var1, boolean var2) {
        d(var0, var1);
        if (logClient != null && open && var2) {
            logClient.showLog(var1);
        }

    }

    public static void v(String var0, String var1) {
        if (logClient != null) {
            logClient.v(var0,var1);
        }
    }

    public static void v(String var0, String var1, boolean var2) {
        v(var0, var1);
        if (logClient != null && open && var2) {
            logClient.showLog(var1);
        }

    }

    public static void setLogView(TextView var0) {
        if (var0 != null && logClient != null) {
            logClient.setLogView(var0);
        }
    }

    public static boolean setTbsLogClient(DLogClient var0) {
        if (var0 == null) {
            return false;
        } else {
            logClient = var0;
            return true;
        }
    }

    public static synchronized void initIfNeed(Context context) {
        if (logClient == null) {
            setTbsLogClient(new DLogClient(context));
        }

    }
}
