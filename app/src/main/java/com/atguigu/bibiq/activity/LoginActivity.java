package com.atguigu.bibiq.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.bibiq.R;
import com.atguigu.bibiq.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @InjectView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @InjectView(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;
    @InjectView(R.id.iv_login_lift)
    ImageView ivLoginLift;
    @InjectView(R.id.iv_login_centre)
    ImageView ivLoginCentre;
    @InjectView(R.id.iv_login_right)
    ImageView ivLoginRight;
    @InjectView(R.id.iv_login_user)
    ImageView ivLoginUser;
    @InjectView(R.id.et_login_user)
    EditText etLoginUser;
    @InjectView(R.id.ll_login_user)
    LinearLayout llLoginUser;
    @InjectView(R.id.iv_login_paw)
    ImageView ivLoginPaw;
    @InjectView(R.id.et_login_paw)
    EditText etLoginPaw;
    @InjectView(R.id.ll_login_paw)
    LinearLayout llLoginPaw;
    @InjectView(R.id.btn_enroll)
    Button btnEnroll;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_login_back, R.id.tv_login_forget_password, R.id.btn_enroll, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_login_forget_password:
                Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_enroll:
                Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_register:
                Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
