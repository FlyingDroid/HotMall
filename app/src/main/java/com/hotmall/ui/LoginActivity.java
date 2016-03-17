package com.hotmall.ui;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.hotmall.R;
import com.hotmall.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    // UI references.
    private AutoCompleteTextView mAccountView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mAccountView = (AutoCompleteTextView) findViewById(R.id.account);


    }

}

