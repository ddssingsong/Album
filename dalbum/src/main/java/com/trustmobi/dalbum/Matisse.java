package com.trustmobi.dalbum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.trustmobi.dalbum.internal.DalbumActivity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

/**
 * 程序使用入口类
 */

public final class Matisse {
    private final WeakReference<Activity> mContext;
    private final WeakReference<Fragment> mFragment;


    private Matisse(Activity activity) {
        this(activity, null);
    }

    private Matisse(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private Matisse(Activity activity, Fragment fragment) {
        mContext = new WeakReference<>(activity);
        mFragment = new WeakReference<>(fragment);
    }

    public static Matisse from(Activity activity) {
        return new Matisse(activity);
    }

    public static Matisse from(Fragment fragment) {
        return new Matisse(fragment);
    }

    //获取Uri列表
    public static List<Uri> obtainResult(Intent data) {
        return data.getParcelableArrayListExtra(DalbumActivity.EXTRA_RESULT_SELECTION);
    }

    //获取路径列表
    public static List<String> obtainPathResult(Intent data) {
        return data.getStringArrayListExtra(DalbumActivity.EXTRA_RESULT_SELECTION_PATH);
    }

    public SelectionCreator choose(Set<MimeType> mimeTypes) {
        return this.choose(mimeTypes, true);
    }


    public SelectionCreator choose(Set<MimeType> mimeTypes, boolean mediaTypeExclusive) {
        return new SelectionCreator(this, mimeTypes, mediaTypeExclusive);
    }
    @Nullable
    Activity getActivity() {
        return mContext.get();
    }

    @Nullable
    Fragment getFragment() {
        return mFragment != null ? mFragment.get() : null;
    }

}
