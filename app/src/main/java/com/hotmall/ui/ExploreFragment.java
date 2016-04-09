package com.hotmall.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotmall.R;
import com.hotmall.base.BaseLazyFragment;
import com.hotmall.library.LibLogger;

public class ExploreFragment extends BaseLazyFragment {


    public ExploreFragment() {
        // Required empty public constructor
        LibLogger.zhshLog().d(TAG);
    }

    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        LibLogger.zhshLog().d(TAG);
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    protected void firstVisibleLoad() {
        LibLogger.zhshLog().d(TAG);

    }
}