package com.trustmobi.componentlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.trustmobi.basiclib.utils.ViewHelper;
import com.trustmobi.componentlib.R;
import com.trustmobi.componentlib.widget.WindowInsetLayout;

/**
 * Created by dds on 2018/1/12.
 * 联信摩贝
 */

public abstract class BaseFragment extends Fragment {

    protected static final TransitionConfig SLIDE_TRANSITION_CONFIG = new TransitionConfig(
            R.anim.slide_in_right, R.anim.slide_out_left,
            R.anim.slide_in_left, R.anim.slide_out_right);

    protected static final TransitionConfig SCALE_TRANSITION_CONFIG = new TransitionConfig(
            R.anim.scale_enter, R.anim.slide_still,
            R.anim.slide_still, R.anim.scale_exit);


    protected abstract View onCreateView();

    public BaseFragment() {
        super();
    }


    //============================= 生命周期 ================================

    private View mBaseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView();
        if (translucentFull()) {
            if (view instanceof WindowInsetLayout) {
                view.setFitsSystemWindows(false);
                mBaseView = view;
            } else {
                mBaseView = new WindowInsetLayout(getActivity());
                ((WindowInsetLayout) mBaseView).addView(view, new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        } else {
            view.setFitsSystemWindows(true);
            mBaseView = view;
        }
        ViewHelper.requestApplyInsets(getActivity().getWindow());
        return mBaseView;
    }

    protected void popBackStack() {
        getBaseFragmentActivity().popBackStack();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!enter && getParentFragment() != null && getParentFragment().isRemoving()) {
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(R.integer.dds_anim_duration);
            return doNothingAnim;
        }
        if (nextAnim != R.anim.scale_enter || !enter) {
            return super.onCreateAnimation(transit, enter, nextAnim);
        }
        try {
            Animation nextAnimation = AnimationUtils.loadAnimation(getContext(), nextAnim);
            nextAnimation.setAnimationListener(new Animation.AnimationListener() {

                private float mOldTranslationZ;

                @Override
                public void onAnimationStart(Animation animation) {
                    if (getView() != null) {
                        mOldTranslationZ = ViewCompat.getTranslationZ(getView());
                        ViewCompat.setTranslationZ(getView(), 100.f);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (getView() != null) {
                        getView().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //延迟回复z-index,如果退出动画更长，这里可能会失效
                                ViewCompat.setTranslationZ(getView(), mOldTranslationZ);
                            }
                        }, 100);

                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            return nextAnimation;
        } catch (Exception ignored) {

        }
        return null;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mBaseView = null;
    }


    //沉浸式处理，返回 false，则状态栏下为内容区域，返回 true, 则状态栏下为 padding 区域
    protected boolean translucentFull() {
        return false;
    }


    //如果是最后一个Fragment，finish后执行的方法
    @SuppressWarnings("SameReturnValue")
    public Object onLastFragmentFinish() {
        return null;
    }

    public TransitionConfig onFetchTransitionConfig() {
        return SLIDE_TRANSITION_CONFIG;
    }

    public boolean isAttachedToActivity() {
        return !isRemoving() && mBaseView != null;
    }


    public final BaseFragmentActivity getBaseFragmentActivity() {
        return (BaseFragmentActivity) getActivity();
    }

    // 显示键盘
    protected void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethod.SHOW_FORCED);
    }

    //隐藏键盘
    protected boolean hideKeyBoard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(getActivity().findViewById(android.R.id.content)
                .getWindowToken(), 0);
    }

}
