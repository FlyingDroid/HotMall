package com.hotmall.ui;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotmall.R;
import com.hotmall.base.BaseLazyFragment;
import com.hotmall.library.LibLogger;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseLazyFragment {


    public MineFragment() {
        // Required empty public constructor
        LibLogger.zhshLog().d(TAG);
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        LibLogger.zhshLog().d(TAG);
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void firstVisibleLoad() {
        LibLogger.zhshLog().d(TAG);

    }
}
