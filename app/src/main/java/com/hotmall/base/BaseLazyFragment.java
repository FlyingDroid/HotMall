package com.hotmall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotmall.library.LibLogger;

/**
 * Created by zhangsheng on 2016/4/9.
 */
public abstract class BaseLazyFragment extends BaseFragment {
    private boolean isVisible;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = getRootView(inflater, container);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected abstract View getRootView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        if (!isVisible) {
            firstVisibleLoad();
            isVisible = true;
        }
    }


    /**
     * 不可见
     */
    protected void onInvisible() {
    }


    /**
     * 延迟加载
     * 子类重写此方法
     */
    protected abstract void firstVisibleLoad();

}
