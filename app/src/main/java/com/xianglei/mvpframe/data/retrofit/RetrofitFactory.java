package com.xianglei.mvpframe.data.retrofit;

import com.xianglei.mvpframe.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class RetrofitFactory {

    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(Constant.GANK_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    /**
     * 获取请求接口的代理对象
     * @return
     */
    public static ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

}
