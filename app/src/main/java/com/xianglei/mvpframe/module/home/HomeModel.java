package com.xianglei.mvpframe.module.home;

import android.widget.Toast;

import com.xianglei.mvpframe.base.inf.ICallBackListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;
import com.xianglei.mvpframe.data.retrofit.RetrofitFactory;
import com.xianglei.mvpframe.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    public void getArticles(final int size,final int page) {
        if(0 == size) {
            return;
        }
        Observable<CommonBean<List<ArticleInfo>>> ob = RetrofitFactory.getApiService().getArticles(size, page);
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
                        mCallBackListener.onFailure();
                    }
                });
    }

}
