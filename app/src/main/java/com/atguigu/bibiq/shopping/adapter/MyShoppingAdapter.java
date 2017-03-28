package com.atguigu.bibiq.shopping.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.UserDao;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.app.MyApplication;
import com.atguigu.bibiq.dao_user.User;
import com.atguigu.bibiq.find.bean.OriginalBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG             #
 * #                                                     #
 * #         QQ/微信: 642609666       李岩              #
 */
public class MyShoppingAdapter extends BaseAdapter {
    private final Context mContext;
    private final UserDao mUserDao;
    private List<OriginalBean.DataBean> mData;
    private long id = 0;
    private boolean isAdd = true;

    public MyShoppingAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_shopping, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(mData.get(position).getCover())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.ivShoppingIcon);
        viewHolder.tvShoppingName.setText(mData.get(position).getName());
        //价格
        double number = (double) mData.get(position).getDanmaku();
        final double price = number / 100;
        viewHolder.tvShoppingPrice.setText("$ " + price);
        //减
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = finalViewHolder.btnNumber.getText().toString();
                int number = Integer.parseInt(text);
                if (number <= 0) {
                    finalViewHolder.btnNumber.setText(number + "");
                } else {
                    finalViewHolder.btnNumber.setText(--number + "");
                }
            }
        });
        //加
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = finalViewHolder.btnNumber.getText().toString();
                int number = Integer.parseInt(text);
                finalViewHolder.btnNumber.setText(++number + "");
            }
        });
        //加入
        viewHolder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = finalViewHolder.btnNumber.getText().toString();
                int number = Integer.parseInt(text);
                if (number > 0) {
                    List<User> users = mUserDao.loadAll();
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getId() == (long) mData.get(position).getFavourite()) {
                            //已经添加 ,此处修改数量
                            User user = users.get(i);
                            int number1 = user.getNumber();
                            user.setNumber(number + number1);
                            mUserDao.update(user);
                            //       mUserDao.update(new User(users.get(i).getId(), users.get(i).getName(), users.get(i).getPrice(), users.get(i).getNumber() + number, users.get(i).getImage()));
                            Log.e("TAG", "修改数量成功");
                            Toast.makeText(mContext, "加入购物车成功", Toast.LENGTH_SHORT).show();
                            isAdd = false;
                            break;
                        } else {
                            isAdd = true;
                        }
                    }


                    if (isAdd) {
                        //添加
                        mUserDao.insert(new User((long) mData.get(position).getFavourite(), mData.get(position).getName(), price, number, mData.get(position).getCover(), true));
                        Log.e("TAG", "加入购物车成功" + mData.get(position).getFavourite());
                        Toast.makeText(mContext, "加入购物车成功", Toast.LENGTH_SHORT).show();
                    }
                    finalViewHolder.btnNumber.setText(0 + "");


//                    String userName = "";
//                    for (int i = 0; i < users.size(); i++) {
//                        userName += users.get(i).getName() + "," + users.get(i).getNumber() + " ;";
//                    }
//                    Log.e("TAG", "查询所有的数据" + userName);
                }
            }
        });
        return convertView;
    }

    public void setData(List<OriginalBean.DataBean> data) {
        mData = data;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_shopping_icon)
        ImageView ivShoppingIcon;
        @InjectView(R.id.tv_shopping_name)
        TextView tvShoppingName;
        @InjectView(R.id.tv_shopping_price)
        TextView tvShoppingPrice;
        @InjectView(R.id.btn_sub)
        Button btnSub;
        @InjectView(R.id.btn_number)
        Button btnNumber;
        @InjectView(R.id.btn_add)
        Button btnAdd;
        @InjectView(R.id.btn_confirm)
        Button btnConfirm;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
