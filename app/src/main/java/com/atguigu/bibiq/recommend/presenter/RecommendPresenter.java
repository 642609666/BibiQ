package com.atguigu.bibiq.recommend.presenter;

import com.atguigu.bibiq.recommend.bean.RecommendBean;
import com.atguigu.bibiq.recommend.model.IOnInstanceDataListener;
import com.atguigu.bibiq.recommend.model.RecommendModel;
import com.atguigu.bibiq.recommend.model.RecommendModelImpl;
import com.atguigu.bibiq.recommend.view.RecommendView;

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

public class RecommendPresenter {
    /**
     * M
     */
    private RecommendModel mRecommendModel;
    /**
     * V
     */
    private RecommendView mRecommendView;

    public RecommendPresenter(RecommendView recommendView) {
        this.mRecommendView = recommendView;
        this.mRecommendModel = new RecommendModelImpl();
    }

    /**
     * 网络下载请求数据
     */
    public void initFromNet() {
        mRecommendModel.instanceData(mRecommendView.getAddress(), new IOnInstanceDataListener() {
            @Override
            public void onInstanceDataSuccess(RecommendBean recommendBean) {
                //隐藏刷新
                mRecommendView.hideRefreshing();
                if (recommendBean != null) {
                    //设置适配器
                    mRecommendView.initAdapter(recommendBean);
                }
            }

            @Override
            public void onInstanceDataFailed(Throwable e) {
                //隐藏刷新
                mRecommendView.hideRefreshing();
            }
        });
    }
}
