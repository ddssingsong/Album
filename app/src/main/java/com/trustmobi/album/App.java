package com.trustmobi.album;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.trustmobi.componentlib.router.Router;

/**
 * Application
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // setupLeakCanary();
        Router.registerComponent("com.trustmobi.dalbum.applike.AlbumLike");

    }


    //初始化LeakCanary
    protected RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

}
