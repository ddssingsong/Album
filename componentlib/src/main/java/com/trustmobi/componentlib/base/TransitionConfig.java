package com.trustmobi.componentlib.base;

/**
 * 界面跳转动画
 * Created by dds on 2018/1/12.
 * 联信摩贝
 */

public class TransitionConfig {
    public final int enter;
    public final int exit;
    public final int popenter;
    public final int popout;

    public TransitionConfig(int enter, int popout) {
        this(enter, 0, 0, popout);
    }

    public TransitionConfig(int enter, int exit, int popenter, int popout) {
        this.enter = enter;
        this.exit = exit;
        this.popenter = popenter;
        this.popout = popout;
    }
}
