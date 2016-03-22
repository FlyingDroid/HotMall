package com.hotmall.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hotmall.R;
import com.hotmall.library.ViewFinder;

/**
 * Created by zhsheng on 2016/1/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected ViewFinder finder;
    protected Activity activity;
    @LayoutRes
    protected int layoutId;

    protected abstract void setLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setLayoutId();
        super.setContentView(layoutId);
        finder = new ViewFinder(this);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        setContentView(R.layout.layout_null_content);
        System.runFinalization();//强制垃圾回收
        super.onDestroy();
    }
}
