package com.trustmobi.basiclib.utils;

import android.graphics.Rect;
import android.support.v4.view.WindowInsetsCompat;

/**
 * @author dds
 * @date 2017-09-13
 */

public interface IWindowInsetLayout {
    boolean applySystemWindowInsets19(Rect insets);

    boolean applySystemWindowInsets21(WindowInsetsCompat insets);
}
