package com.xianglei.mvpframe.module.home;

import com.xianglei.mvpframe.base.BasePresenter;
import com.xianglei.mvpframe.base.inf.ICallBackListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;

import java.util.List;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter,
        ICallBackListener<List<ArticleInfo>> {

    private static final String TAG = "HomePresenter";

    @Override
    public HomeContract.Model createModel() {
        return new HomeModel(this);
    }

    @Override
    public void onSuccess(List<ArticleInfo> data) {
        if(getView() != null){
            getView().dismissLoadingDialog();
            getView().setArticleList(data);
        }
    }

    @Override
    public void onFailure() {
        if(getView() != null){
            getView().dismissLoadingDialog();
        }
    }

    @Override
    public void getArticles(int size, int page) {
        if(getModel() != null){
            getView().showLoadingDialog();
            getModel().getArticles(size, page);
        }
    }
}
