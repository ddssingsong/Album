package com.trustmobi.basiclib;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.trustmobi.basiclib.log.DLog;
import com.trustmobi.basiclib.utils.AppUtil;
import com.trustmobi.basiclib.utils.CompareUtil;

import java.io.File;

public class UtilActivity extends AppCompatActivity {
    public static final String TAG = "dds";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);
        textView = (TextView) findViewById(R.id.text);
        DLog.initIfNeed(this);
        DLog.setLogView(textView);

        //versionName
        String verName = AppUtil.getVerName(this);
        DLog.d(TAG, "version_name:" + verName, true);

        //versionCode
        int verCode = AppUtil.getVerCode(this);
        DLog.d(TAG, "version_code：" + verCode, true);

        //获取AndroidManifest中的meta值
        String meta = AppUtil.getMetaData(this, "mata_test");
        DLog.d(TAG, "meta：" + meta, true);

        //deviceId   需要权限
        String deviceId = AppUtil.getDeviceId(this);
        DLog.d(TAG, "deviceId：" + deviceId, true);

        //SIM_id  需要权限
        String simId = AppUtil.getSimId(this);
        DLog.d(TAG, "simId：" + simId, true);

        //cpu
        String cpu = AppUtil.getCpu();
        DLog.d(TAG, "cpu：" + cpu, true);

        //wifi mac
        String mac = AppUtil.getWifiMac(this);
        DLog.d(TAG, "wifi mac：" + mac, true);

        //android_id
        String androidId = AppUtil.getAndroidId(this);
        DLog.d(TAG, "android_id：" + androidId);

//        String a = AppUtil.a(this, new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "test.apk"));
//        DLog.d(TAG, a + "", true);
        String a1 = AppUtil.getApkSignatures(this, new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "mixin.apk"));
        DLog.d(TAG, "Signatures：" + a1, true);

        boolean flag = CompareUtil.compareApk(this,new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "mixin.apk"),new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "mixin.apk").length(),62);
        DLog.d(TAG,flag?"是密信":"不是密信",true);

    }
}
