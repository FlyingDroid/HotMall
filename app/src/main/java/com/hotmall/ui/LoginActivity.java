package com.hotmall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.hotmall.R;
import com.hotmall.SplashActivity;
import com.hotmall.base.BaseActivity;
import com.hotmall.library.LibLogger;

public class LoginActivity extends BaseActivity {

    // UI references.
    private AutoCompleteTextView mAccountView;
    private EditText mPasswordView;

    @Override
    protected void setLayoutId() {
        layoutId = R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the login form.
        mAccountView = (AutoCompleteTextView) findViewById(R.id.account);
        Button button = finder.find(R.id.email_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibLogger.zhshLog().d(activity);
                startActivity(new Intent(activity, SplashActivity.class));
            }
        });

    }

}

