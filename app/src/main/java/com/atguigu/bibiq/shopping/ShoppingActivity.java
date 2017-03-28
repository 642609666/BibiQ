package com.atguigu.bibiq.shopping;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.UserDao;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.app.MyApplication;
import com.atguigu.bibiq.base.BaseActivity;
import com.atguigu.bibiq.dao_user.User;
import com.atguigu.bibiq.shopping.adapter.MyShoppingAdapter1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.atguigu.bibiq.R.id.ll_confirm;

public class ShoppingActivity extends BaseActivity {

    @InjectView(R.id.iv_web_back)
    ImageView ivWebBack;
    @InjectView(R.id.tv_web_name)
    TextView tvWebName;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.rb_isshow)
    CheckBox rbIsshow;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    @InjectView(ll_confirm)
    LinearLayout llConfirm;
    @InjectView(R.id.rb_delete)
    CheckBox rbDelete;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.ll_delete)
    LinearLayout llDelete;
    @InjectView(R.id.ll_empty)
    LinearLayout ll_empty;
    @InjectView(R.id.gridview)
    GridView gridview;
    private MyShoppingAdapter1 mAdapter;
    private UserDao mUserDao;
    private List<User> mList;
    private boolean isMore;
    private double price;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping;
    }

    @Override
    protected void initData() {
        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
        mAdapter = new MyShoppingAdapter1(ShoppingActivity.this);
        gridview.setAdapter(mAdapter);
        mList = new ArrayList<>();
        showEmpty();
        //得到集合数据
        initList();

        getPrice();
        rbDelete.setChecked(query());
        rbIsshow.setChecked(query());
    }

    /**
     * 如果数据为空 显示图片
     */

    private void showEmpty() {
        if (mUserDao.loadAll().size() == 0) {
            gridview.setVisibility(View.GONE);
            tvMore.setVisibility(View.GONE);
            llConfirm.setVisibility(View.GONE);
            llDelete.setVisibility(View.GONE);
            ll_empty.setBackgroundResource(R.drawable.ic_empty_cute_girl_box);
            // ll_empty.setClickable(true);
        } else {
            gridview.setVisibility(View.VISIBLE);
            tvMore.setVisibility(View.VISIBLE);
            llConfirm.setVisibility(View.VISIBLE);
            ll_empty.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //ll_empty.setClickable(false);
        }
    }

    @Override
    protected void initListener() {
        /**
         * 单个itme点击回调
         */
        if (mAdapter != null) {
            mAdapter.setOnChekebox(new MyShoppingAdapter1.OnChekebox() {
                @Override
                public void onlistener(Boolean aBoolean, Long aLong) {
                    getPrice();
                }
            });
        }
        /**
         * 效验item是否全部点击回调
         */
        if (mAdapter != null) {
            mAdapter.setOnallChekebox(new MyShoppingAdapter1.OnAllChekebox() {
                @Override
                public void onAlllistener(Boolean aBoolean) {
                    rbDelete.setChecked(aBoolean);
                    rbIsshow.setChecked(aBoolean);
                    getPrice();
                }
            });
        }
        /**
         * 改变数值的回调监听
         */
        if (mAdapter != null) {
            mAdapter.setOnNumberChekebox(new MyShoppingAdapter1.setOnNumberChekebox() {
                @Override
                public void onNumberlistener() {
                    getPrice();
                    Log.e("TAG", "监听");
                }
            });
        }
        rbIsshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                isCheckedShow(isChecked);
                getPrice();
            }
        });
        rbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                isCheckedShow(isChecked);

            }
        });
    }

    private void initList() {
        List<User> users = mUserDao.loadAll();
        if (mList != null) {
            mList.clear();
        }
        for (int i = 0; i < users.size(); i++) {
            mList.add(users.get(i));
            price += users.get(i).getPrice() * users.get(i).getNumber();
        }
        if (mList != null && mAdapter != null) {
            mAdapter.setData(mList);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 得到金钱数量
     */
    private void getPrice() {
        price = 0;
        //得到数据库数据
        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMBoolean()) {
                price += users.get(i).getPrice() * users.get(i).getNumber();
            }
        }
        tvPrice.setText("$:" + new DecimalFormat("######0.00").format(price) + "");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.iv_web_back, R.id.ll_empty, R.id.tv_more, R.id.btn_confirm, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.ll_empty:
                // startActivity(new Intent(this, MainActivity.class));
                //  finish();
                break;
            case R.id.tv_more:
                if (!isMore) {
                    isMore = !isMore;
                    tvMore.setText("编辑");
                    llDelete.setVisibility(View.VISIBLE);
                    llConfirm.setVisibility(View.GONE);
                } else {
                    isMore = !isMore;
                    llDelete.setVisibility(View.GONE);
                    llConfirm.setVisibility(View.VISIBLE);
                    tvMore.setText("完成");
                }
                break;
            case R.id.btn_confirm:
                Toast.makeText(ShoppingActivity.this, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                isDelete();
                getPrice();
                showEmpty();
                break;
        }
    }

    /**
     * 删除选中的数据
     */
    private void isDelete() {
        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMBoolean() == true) {
                mUserDao.deleteByKey(users.get(i).getId());
            }
        }
        initList();
    }

    /**
     * 改变item所有chebox
     *
     * @param b
     */
    public void isCheckedShow(boolean b) {
        List<User> users = mUserDao.loadAll();
        if (query() || b) {
            mList.clear();
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                user.setMBoolean(b);
                mUserDao.update(user);
                mList.add(user);
            }
            if (mAdapter != null) {
                mAdapter.setData(mList);
                mAdapter.notifyDataSetChanged();
            }
        } else if (queryFalse()) {
            mList.clear();
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                user.setMBoolean(b);
                mUserDao.update(user);
                mList.add(user);
            }
            if (mAdapter != null) {
                mAdapter.setData(mList);
                mAdapter.notifyDataSetChanged();
            }
        }

    }

    /**
     * 查询是否全部选中
     *
     * @return
     */
    public boolean query() {
        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMBoolean() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查询是否全部未选中
     *
     * @return
     */
    public boolean queryFalse() {
        List<User> users = mUserDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMBoolean() == true) {
                return false;
            }
        }
        return true;
    }
}
