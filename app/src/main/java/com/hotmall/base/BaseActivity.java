package com.hotmall.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Space;
import android.widget.Toast;

import com.hotmall.R;
import com.hotmall.library.ViewFinder;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhsheng on 2016/1/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected ViewFinder finder;
    protected Activity activity;
    private CompositeSubscription mCompositeSubscription;
    private Toast toast;

    protected abstract int setLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        if (setLayoutId() != 0) {
            super.setContentView(setLayoutId());
        } else {
            super.setContentView(new Space(activity));
        }
        finder = new ViewFinder(this);
        initToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
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
        setContentView(new Space(this));
        System.runFinalization();//强制垃圾回收
        super.onDestroy();
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    protected void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    protected void showToast(@StringRes int msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
