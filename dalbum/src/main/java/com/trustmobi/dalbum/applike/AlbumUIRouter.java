package com.trustmobi.dalbum.applike;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trustmobi.componentlib.router.ui.IComponentRouter;
import com.trustmobi.dalbum.Matisse;
import com.trustmobi.dalbum.MimeType;
import com.trustmobi.dalbum.engine.impl.GlideEngine;
import com.trustmobi.dalbum.internal.entity.CaptureStrategy;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dds on 2017/6/20.
 */

public class AlbumUIRouter implements IComponentRouter {
    private static final int REQUEST_CODE_CHOOSE = 23;


    private static final String SCHEME = "trustmobi";
    private static final String SHARE_HOST = "album";

    private static String[] HOSTS = new String[]{SHARE_HOST};
    private static AlbumUIRouter instance = new AlbumUIRouter();

    private AlbumUIRouter() {

    }

    public static AlbumUIRouter getInstance() {
        return instance;
    }

    @Override
    public boolean openUri(Context context, String url, Bundle bundle) {
        if (TextUtils.isEmpty(url) || context == null) {
            return true;
        }
        return openUri(context, Uri.parse(url), bundle);
    }

    @Override
    public boolean openUri(final Context context, Uri uri, Bundle bundle) {
        if (uri == null || context == null) {
            return true;
        }
        String host = uri.getHost();
        if (SHARE_HOST.equals(host)) {
            RxPermissions rxPermissions = new RxPermissions((Activity) context);
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                Matisse.from((Activity) context)
                                        .choose(MimeType.ofImage(), true)
                                        .capture(true)
                                        .captureStrategy(
                                                new CaptureStrategy(true, context.getPackageName() + ".fileProvider"))
                                        .showSingleMediaType(true)
                                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                        .imageEngine(new GlideEngine())
                                        .forResult(REQUEST_CODE_CHOOSE);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyUri(Uri uri) {
        String scheme = uri.getScheme();
        String host = uri.getHost();
        if (SCHEME.equals(scheme)) {
            for (String str : HOSTS) {
                if (str.equals(host)) {
                    return true;
                }
            }
        }
        return false;
    }
}
