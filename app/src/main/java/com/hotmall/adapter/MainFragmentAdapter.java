package com.hotmall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hotmall.base.BaseFragment;

import java.util.List;

/**
 * Created by zhangsheng on 2016/4/9.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter{
    private List<BaseFragment> mFragmentList;

    public MainFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (mFragmentList == null || mFragmentList.size() <= position) ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
