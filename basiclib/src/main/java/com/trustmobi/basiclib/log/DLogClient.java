package com.trustmobi.basiclib.log;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DLogClient {
    private TextView textView;
    private SimpleDateFormat dateFormat = null;
    private Context context = null;

    public DLogClient(Context context) {
        try {
            this.context = context.getApplicationContext();
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", Locale.US);
        } catch (Exception var3) {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        }

    }

    public void showLog(String var1) {
        if(this.textView != null) {
            this.textView.post(new DLogClient.a(var1));
        }

    }

    public void setLogView(TextView var1) {
        this.textView = var1;
    }

    public void i(String var1, String var2) {
        Log.i(var1, var2);
    }

    public void e(String var1, String var2) {
        Log.e(var1, var2);
    }

    public void w(String var1, String var2) {
        Log.w(var1, var2);
    }

    public void d(String var1, String var2) {
        Log.d(var1, var2);
    }

    public void v(String var1, String var2) {
        Log.v(var1, var2);
    }

    private class a implements Runnable {
        String a = null;

        a(String var2) {
            this.a = var2;
        }

        public void run() {
            if(DLogClient.this.textView != null) {
                DLogClient.this.textView.append(this.a + "\n");
            }

        }
    }
}
