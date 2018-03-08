package com.trustmobi.basiclib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.trustmobi.basiclib.utils.AppUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Util {

    public static final String TAG = "dds";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.trustmobi.basiclib.test", appContext.getPackageName());
    }

    @Test
    public void AppUtil() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        //versionName
        String verName = AppUtil.getVerName(appContext);
        assertEquals(verName, "1.0.0");
        Log.d(TAG, "version_name:" + verName);

        //==========================================AppUtil=========================================
        //versionCode
        int verCode = AppUtil.getVerCode(appContext);
        assertEquals(verCode, 1);
        Log.d(TAG, "version_code:" + verCode);

        //获取AndroidManifest中的meta值
        String meta = AppUtil.getMetaData(appContext, "mata_test");
        assertEquals(meta, "mata_test_value");
        Log.d(TAG, meta);

        //deviceId   需要权限
        String deviceId = AppUtil.getDeviceId(appContext);
        Log.d(TAG, "deviceId：" + deviceId);

        //SIM_id  需要权限
        String simId = AppUtil.getSimId(appContext);
        Log.d("dds", "simId:" + simId);

        //cpu
        String cpu = AppUtil.getCpu();
        Log.d("dds", "cpu:" + cpu);

        //wifi mac
        String mac = AppUtil.getWifiMac(appContext);
        Log.d(TAG, "wifi mac" + mac);


    }

}
