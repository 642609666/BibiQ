package com.atguigu.bibiq.utils.utils_rx_net;

import com.atguigu.bibiq.bean.HomeBean;
import com.atguigu.bibiq.bean.HomeStreamingBean;
import com.atguigu.bibiq.recommend.bean.RecommendBean;
import com.atguigu.bibiq.utils.ConstantAddress;

import io.reactivex.Observable;
import retrofit2.http.GET;

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

public interface IRetrofitDwon {
    /**
     * 获得直播数据
     *
     * @return
     */
    @GET("")
    Observable<HomeBean> getStreaming();

    /**
     * 获得推荐数据
     * <RecommendBean>需要解析成功的bean
     * getRecommendBean() 方法名
     * ConstantAddress.BBQ_RECOMMERD_END  跟网站链接的后续网址
     *
     * @return
     */
    @GET(ConstantAddress.BBQ_RECOMMERD_END)
    Observable<RecommendBean> getRecommendBean();

    /**
     * 获得直播数据
     *
     * @return
     */
    @GET(ConstantAddress.STREAMING_END)
    Observable<HomeStreamingBean> getHomeStreamingBean();

    /**
     * 获得首页数据
     *
     * @return
     */
    @GET(ConstantAddress.BBQ_HOME_END)
    Observable<HomeBean> getHomeBean();

}
