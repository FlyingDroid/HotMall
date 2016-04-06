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

import com.hotmall.base.BaseActivity;
import com.hotmall.ui.LoginActivity;
import com.hotmall.ui.MainActivity;
import com.hotmall.utils.SharedPre;

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
                // 是否已经登录过
                if (!outer.judgeLoginAccount()) {
                    outer.goLogin();
                } else {
                    outer.goMain();
                }
            }
        }
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ivLauncherBanner = finder.imageView(R.id.iv_launcher_banner);
        finder.find(R.id.btn_detail).setOnClickListener(this);
        finder.find(R.id.btn_next).setOnClickListener(this);
        ivLauncherBanner.setImageResource(R.mipmap.four_season);
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(MSG_NEXT, 3000);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail:
                break;
            case R.id.btn_next:
                myHandler.removeMessages(MSG_NEXT);
                goLogin();
                break;
        }
    }

    private void goLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void goMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 判断用户是否已经登录账户
     *
     * @return
     */
    private boolean judgeLoginAccount() {
        String userId = SharedPre.getUserId();
        return !(TextUtils.isEmpty(userId));
    }
}
