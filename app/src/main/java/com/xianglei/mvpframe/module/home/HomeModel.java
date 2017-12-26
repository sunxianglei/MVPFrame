package com.xianglei.mvpframe.module.home;

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
    public void getArticles(int size, int page) {
        if(1 == page){  //刷新了
            mArticles.clear();
        }
        if(0 == size) return;
        Observable<CommonBean<List<ArticleInfo>>> ob = RetrofitFactory.getApiService().getArticles(size, page);
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonBean<List<ArticleInfo>>>() {
                    @Override
                    public void accept(CommonBean<List<ArticleInfo>> listCommonBean) throws Exception {
                        Logger.d(TAG, "accept");
                        mArticles.addAll(listCommonBean.getResults());
                        mCallBackListener.onSuccess(mArticles);
                    }
                });
    }

}
