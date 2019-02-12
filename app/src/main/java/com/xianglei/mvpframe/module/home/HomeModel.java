package com.xianglei.mvpframe.module.home;

import com.xianglei.mvpframe.base.inf.ICallBackListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;
import com.xianglei.mvpframe.data.retrofit.RetrofitFactory;
import com.xianglei.mvpframe.utils.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomeModel implements HomeContract.Model{

    private static final String TAG = "HomeModel";
    private ICallBackListener mCallBackListener;
    private List<ArticleInfo> mArticles;

    public HomeModel(ICallBackListener listener){
        this.mCallBackListener = listener;
        mArticles = new ArrayList<>();
    }

    @Override
    public void getArticles(String type, final int size,final int page) {
        if(0 == size) {
            return;
        }
        Observable<CommonBean<List<ArticleInfo>>> ob = RetrofitFactory.getApiService().getArticles(type,size, page);
        ob.subscribeOn(Schedulers.io())
                .map(new Function<CommonBean<List<ArticleInfo>>, List<ArticleInfo>>() {
                    @Override
                    public List<ArticleInfo> apply(CommonBean<List<ArticleInfo>> listCommonBean) throws Exception {
                        if(listCommonBean != null && !listCommonBean.getError()){
                            return listCommonBean.getResults();
                        }
                        Logger.d(TAG, "无数据返回");
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ArticleInfo>>() {
                    @Override
                    public void accept(List<ArticleInfo> articleInfos) throws Exception {
                        Logger.d(TAG, "accept");
                        if(1 == page){  //刷新了
                            mArticles.clear();
                        }
                        mArticles.addAll(articleInfos);
                        mCallBackListener.onSuccess(mArticles);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.d(TAG, "onFailure");
                        mCallBackListener.onFailure();
                    }
                });

//        Call<String> call = RetrofitFactory.getApiService().getArticlesTest("Android", 10, 1);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                System.out.println("====================\n" + response.body() + "\n=========================");
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    System.out.println("====================\n" + response.body().string() + "\n=========================");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }

}
