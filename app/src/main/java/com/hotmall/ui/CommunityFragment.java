package com.hotmall.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotmall.R;
import com.hotmall.base.BaseLazyFragment;

public class CommunityFragment extends BaseLazyFragment {

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    protected void firstVisibleLoad() {
    }
}
