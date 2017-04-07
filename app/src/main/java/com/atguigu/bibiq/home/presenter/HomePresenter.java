package com.atguigu.bibiq.home.presenter;

import android.util.Log;

import com.atguigu.bibiq.bean.HomeBean;
import com.atguigu.bibiq.bean.HomeStreamingBean;
import com.atguigu.bibiq.home.model.HomeModel;
import com.atguigu.bibiq.home.model.HomeModelImpl;
import com.atguigu.bibiq.home.model.IOnInstanceDataHomeListener;
import com.atguigu.bibiq.home.model.IOnInstanceDataStreamingListener;
import com.atguigu.bibiq.home.view.HomeView;

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

public class HomePresenter {
    /**
     * M
     */
    private HomeModel mHomeModel;
    /**
     * V
     */
    private HomeView mHomeView;

    public HomePresenter(HomeView mHomeView) {
        this.mHomeView = mHomeView;
        this.mHomeModel = new HomeModelImpl();
    }

    /**
     * 网络下载请求数据
     */
    public void initFromNet() {
        mHomeModel.instanceData(mHomeView.getAddress(), new IOnInstanceDataStreamingListener() {
            @Override
            public void onInstanceDataSuccess(HomeStreamingBean homeStreamingBean) {

                if (homeStreamingBean != null) {
                    //设置适配器
                    mHomeView.initStreamingAdapter(homeStreamingBean);
                    Log.e("TAG", "直播数据成功");
                }
                //隐藏刷新
                mHomeView.hideRefreshing();
            }

            @Override
            public void onInstanceDataFailed(Throwable e) {
                //隐藏刷新
                mHomeView.hideRefreshing();
                Log.e("TAG", "直播数据失败");
            }
        });
        mHomeModel.instanceHomeData(mHomeView.getAddress(), new IOnInstanceDataHomeListener() {
            @Override
            public void onInstanceDataSuccess(HomeBean homeBean) {

                if (homeBean != null) {
                    //设置适配器
                    mHomeView.initHomeAdapter(homeBean);
                    Log.e("TAG", "主页数据成功");

                }
                //隐藏刷新
                mHomeView.hideRefreshing();

            }

            @Override
            public void onInstanceDataFailed(Throwable e) {
                //隐藏刷新
                mHomeView.hideRefreshing();
                Log.e("TAG", "主页数据失败");
            }
        });
    }
}
