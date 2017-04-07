package com.atguigu.bibiq.utils.utils_rx_net;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

public class RxAndroidOrRetrofit {
    public RxAndroidOrRetrofit() {
    }

    /**
     * rx + retrofit
     *
     * @param path
     * @return
     */
    public static IRetrofitDwon getIRetrofitDwon(final String path) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(path)
                //将JSON格式的数据转换为Java-BEAN 而Retrofit如果要执行这种转换是要依赖于另一个库的，所以我们还得在项目中配置另一个依赖：
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        IRetrofitDwon iRetrofitDwon = retrofit.create(IRetrofitDwon.class);
        return iRetrofitDwon;
    }

    /**
     * 单独用rX
     *
     * @param path
     * @return
     */
    public static Observable getObservable(final String path) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {


                OkHttpUtils.get()
                        .url(path)
                        .tag(this)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("TAG", "RxAndroidOrRetrofit onError()" + e.getMessage());
                                emitter.onError(e);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //发射
                                emitter.onNext(response);
                                Log.e("TAG", "RxAndroidOrRetrofit onResponse()");
                                //完成
                                emitter.onComplete();

                            }
                        });
            }
        });
    }
}
