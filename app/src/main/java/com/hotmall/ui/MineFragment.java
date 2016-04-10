package com.hotmall.ui;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotmall.R;
import com.hotmall.base.BaseLazyFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseLazyFragment {

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void firstVisibleLoad() {
    }
}
