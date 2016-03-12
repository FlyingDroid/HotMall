package com.hotmall.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotmall.R;
import com.hotmall.base.BaseFragment;

public class ReadFragment extends BaseFragment {
    public ReadFragment() {
        // Required empty public constructor
    }

    public static ReadFragment newInstance() {
        return new ReadFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read, container, false);
    }

}
