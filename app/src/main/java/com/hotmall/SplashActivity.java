package com.hotmall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hotmall.base.BaseActivity;
import com.hotmall.common.UserPreference;
import com.hotmall.ui.LoginActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity implements View.OnClickListener {


    public static final int MSG_NEXT = 100;
    private ImageView ivLauncherBanner;
    private MyHandler myHandler;

    static class MyHandler extends Handler {
        // WeakReference to the outer class's instance.
        private WeakReference<SplashActivity> mOuter;

        public MyHandler(SplashActivity activity) {
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity outer = mOuter.get();
            if (outer != null) {
                outer.launcherNextStep();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivLauncherBanner = finder.imageView(R.id.iv_launcher_banner);
        finder.find(R.id.btn_detail).setOnClickListener(this);
        finder.find(R.id.btn_next).setOnClickListener(this);
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(MSG_NEXT, 3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Glide.with(this)
                .load("http://tse2.mm.bing.net/th?id=OIP.M61f994f7c460b60f94c7c70e6f87be0do2&pid=15.1")
                .into(ivLauncherBanner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail:
                break;
            case R.id.btn_next:
                myHandler.removeMessages(MSG_NEXT);
                launcherNextStep();
                break;
        }
    }

    private void launcherNextStep() {
        if (judgeLoginAccount()) {//进入主界面
            startActivity(new Intent(this, LoginActivity.class));
        } else {//进入登录注册
        }
        finish();
    }

    /**
     * 判断用户是否已经登录账户
     *
     * @return
     */
    private boolean judgeLoginAccount() {
        UserPreference userPreference = new UserPreference(activity);
        String userAccount = userPreference.getString(UserPreference.USER_ACCOUNT, "");
        String userPassword = userPreference.getString(UserPreference.USER_PASSWORD, "");
        return !(TextUtils.isEmpty(userAccount) && !TextUtils.isEmpty(userPassword));
    }
}
