package com.hotmall.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hotmall.R;
import com.hotmall.base.BaseActivity;
import com.hotmall.library.encrypt.MD5;
import com.hotmall.utils.SharedPre;
import com.hotmall.widget.LoadingDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.email)
    AutoCompleteTextView emailView;
    @Bind(R.id.password)
    EditText passwordView;
    @Bind(R.id.email_sign_in_button)
    Button emailSignInButton;
    @Bind(R.id.login_form)
    ScrollView loginForm;
    @Bind(R.id.tv_register_account)
    TextView tvRegisterAccount;
    @Bind(R.id.tv_forget_pw)
    TextView tvForgetPw;
    private LoadingDialog loadingDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == KeyEvent.KEYCODE_ENDCALL || actionId == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

    }

    @OnClick(R.id.email_sign_in_button)
    public void clickLogin() {
        attemptLogin();
    }

    private void attemptLogin() {
        // Reset errors.
        emailView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a loading_background task to
            // perform the user login attempt.
            showProgress(true);
            //提交登录信息
            SharedPre.setUserAccount(email);
            SharedPre.setUserPW(password);
            SharedPre.setUserId(MD5.encrypt(email + password));
            //验证成功
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



}

