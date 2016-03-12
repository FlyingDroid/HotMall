package com.hotmall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hotmall.ui.MediaFragment;
import com.hotmall.ui.ReadFragment;
import com.hotmall.ui.ShopFragment;

/**
 * Created by zhsheng on 2016/1/18.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ReadFragment.newInstance();
                break;
            case 1:
                fragment = ShopFragment.newInstance();
                break;
            case 2:
                fragment = MediaFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
