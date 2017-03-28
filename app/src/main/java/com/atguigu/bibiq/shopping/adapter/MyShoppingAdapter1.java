package com.atguigu.bibiq.shopping.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.UserDao;
import com.atguigu.bibiq.R;
import com.atguigu.bibiq.app.MyApplication;
import com.atguigu.bibiq.dao_user.User;
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
public class MyShoppingAdapter1 extends BaseAdapter {
    private final Context mContext;
    private final UserDao mUserDao;
    private boolean isAdd = true;
    private List<User> mData;

    public MyShoppingAdapter1(Context context) {
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
            convertView = View.inflate(mContext, R.layout.adapter_shopping1, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(mData.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewHolder.ivShoppingIcon);
        viewHolder.tvShoppingName.setText(mData.get(position).getName());
        //价格
        viewHolder.tvShoppingPrice.setText("$ " + mData.get(position).getPrice());

        viewHolder.btnNumber.setText(mData.get(position).getNumber() + "");

        viewHolder.checkbox.setChecked(mData.get(position).getMBoolean());


        //减
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = finalViewHolder.btnNumber.getText().toString();
                int number = Integer.parseInt(text);
                if (number <= 1) {
                    finalViewHolder.btnNumber.setText(number + "");
                } else {
                    finalViewHolder.btnNumber.setText(--number + "");
                }

                //改变数值后,监听
                isNumber(position,number);
                if(onNumberChekebox!= null) {
                    onNumberChekebox.onNumberlistener();
                }


//                if (number == 0) {
//                    finalViewHolder.checkbox.setChecked(false);
//                    isTrue(position, false);
//                }

                //如果全部选中就传递给主界面
//                if (query()) {
//                    if (mOnallChekebox != null) {
//                        mOnallChekebox.onAlllistener(true);
//                    }
//                } else {
//                    if (mOnallChekebox != null) {
//                        mOnallChekebox.onAlllistener(false);
//                    }
//                }

            }
        });


        //加
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = finalViewHolder.btnNumber.getText().toString();
                int number = Integer.parseInt(text);
                number += 1;
                finalViewHolder.btnNumber.setText(number + "");
               // finalViewHolder.checkbox.setChecked(true);
                Log.e("TAG", "加 " + number);
               // isTrue(position, true);
                //如果全部选中就传递给主界面
//                if (query()) {
//                    if (mOnallChekebox != null) {
//                        mOnallChekebox.onAlllistener(true);
//                    }
//                } else {
//                    if (mOnallChekebox != null) {
//                        mOnallChekebox.onAlllistener(false);
//                    }
//                }

                //改变数值后,监听
                isNumber(position,number);
                if(onNumberChekebox!= null) {
                    onNumberChekebox.onNumberlistener();
                }

            }
        });
        viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isTrue(position, isChecked);
                if (mOnChekebox != null) {
                    mOnChekebox.onlistener(isChecked, mData.get(position).getId());
                }
                //如果全部选中就传递给主界面
                if (query()) {
                    if (mOnallChekebox != null) {
                        mOnallChekebox.onAlllistener(true);
                    }
                } else {
                    if (mOnallChekebox != null) {
                        mOnallChekebox.onAlllistener(false);
                    }
                }
            }
        });

        return convertView;
    }

    /**
     * 修改数据库,变为flase
     *
     * @param position
     */
    private void isTrue(int position, boolean b) {
        User user = mData.get(position);
        user.setMBoolean(b);
        mUserDao.update(user);
    }
    /**
     * 修改数据库的数量
     *
     * @param position
     */
    private void isNumber(int position, int number) {
        User user = mData.get(position);
        user.setNumber(number);
        mUserDao.update(user);
        Log.e("TAG", "改变" + user.getNumber());
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

    public void setData(List<User> data) {
        mData = data;
    }


    static class ViewHolder {
        @InjectView(R.id.checkbox)
        CheckBox checkbox;
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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    /**
     * 单个选中的回调
     *
     * @param onChekebox
     */
    public void setOnChekebox(OnChekebox onChekebox) {
        mOnChekebox = onChekebox;
    }

    private OnChekebox mOnChekebox;

    public interface OnChekebox {
        void onlistener(Boolean aBoolean, Long aLong);
    }


    /**
     * 全部选中的回调
     *
     * @param onallChekebox
     */
    public void setOnallChekebox(OnAllChekebox onallChekebox) {
        mOnallChekebox = onallChekebox;
    }

    private OnAllChekebox mOnallChekebox;

    public interface OnAllChekebox {
        void onAlllistener(Boolean aBoolean);
    }



    /**
     * 改变数值的选中的回调
     *
     * @param onNumberChekebox
     */
    public void setOnNumberChekebox(setOnNumberChekebox onNumberChekebox) {
        this.onNumberChekebox = onNumberChekebox;
    }
    private setOnNumberChekebox onNumberChekebox;

    public interface setOnNumberChekebox {
        void onNumberlistener();
    }
}
