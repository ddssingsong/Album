package com.trustmobi.album.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.trustmobi.album.R;
import com.trustmobi.basiclib.log.DLog;
import com.trustmobi.componentlib.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dds on 2018/1/17.
 * 联信摩贝
 */

public class HomeFragment extends BaseFragment {
    @Override
    protected View onCreateView() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, layout);
        return layout;
    }

    @OnClick(R.id.btn_album)
    public void album() {
        DLog.d("dds","album");
    }


}
