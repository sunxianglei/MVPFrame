package com.xianglei.mvpframe.data.retrofit;

import android.net.ConnectivityManager;

import com.xianglei.mvpframe.utils.Config;
import com.xianglei.mvpframe.utils.Const;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class RetrofitFactory {

    private static final int DEFAULT_TIMEOUT = 10;

    private static Retrofit mRetrofit;

    public static Retrofit getInstance(){
        return RetrofitFactoryHolder.instance;
    }

    private static class RetrofitFactoryHolder{
        private static final Retrofit instance = getInstance();

        private static Retrofit getInstance(){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if(Config.DEBUG){
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            }else{
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(interceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Const.GANK_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit;
        }
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofit == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    if(Config.DEBUG){
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    }else{
                        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }
                    OkHttpClient.Builder client = new OkHttpClient.Builder()
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .addInterceptor(interceptor);
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(Const.GANK_URL)
                            .client(client.build())
//                            .addConverterFactory(GsonConverterFactory.create())
                            .addConverterFactory(new StringConverterFactory())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
