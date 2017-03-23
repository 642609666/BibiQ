package com.atguigu.bibiq.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

/**
 * 登录界面
 */
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

        //账户信息焦点事件
        etListener();


    }

    private void etListener() {
        etLoginUser.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    ivLoginLift.setImageResource(R.drawable.ic_22);
                    ivLoginRight.setImageResource(R.drawable.ic_33);
                    ivLoginUser.setImageResource(R.drawable.ic_login_username_default_meitu_3);
                    ivLoginPaw.setImageResource(R.drawable.ic_login_password_default);
                    llLoginUser.setBackgroundColor(Color.parseColor("#FB7299"));
                    llLoginPaw.setBackgroundColor(Color.parseColor("#778B8987"));
                } else {
                    // 此处为失去焦点时的处理内容
                    ivLoginLift.setImageResource(R.drawable.ic_22_hide);
                    ivLoginRight.setImageResource(R.drawable.ic_33_hide);
                    ivLoginUser.setImageResource(R.drawable.ic_login_username_default);
                    ivLoginPaw.setImageResource(R.drawable.ic_login_password_default_meitu_3);
                    llLoginUser.setBackgroundColor(Color.parseColor("#778B8987"));
                    llLoginPaw.setBackgroundColor(Color.parseColor("#FB7299"));
                }
            }
        });
        //设置监听输入,改变登录按钮颜色和响应
        etLoginUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isListener();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //设置监听输入
        etLoginPaw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isListener();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void isListener() {
        if (!TextUtils.isEmpty(etLoginUser.getText().toString()) && !TextUtils.isEmpty(etLoginPaw.getText().toString())) {
            btnRegister.setBackgroundResource(R.drawable.btn_register_shape_s1);
            //设置点击是否好使
            btnRegister.setClickable(true);

        } else {
            btnRegister.setBackgroundResource(R.drawable.btn_register_shape_s);
            btnRegister.setClickable(false);
        }
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
