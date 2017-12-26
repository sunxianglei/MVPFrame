package com.xianglei.mvpframe.module.home;

import com.xianglei.mvpframe.base.inf.ICallBackListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;

import java.util.List;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomePresenter implements HomeContract.Presenter, ICallBackListener<List<ArticleInfo>> {

    private static final String TAG = "HomePresenter";

    private HomeContract.Model mModel;
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view){
        this.mView = view;
        mModel = new HomeModel(this);
    }

    @Override
    public void onSuccess(List<ArticleInfo> data) {
        if(mView != null){
            mView.dismissLoadingDialog();
            mView.setArticleList(data);
        }
    }

    @Override
    public void onFailure() {
        if(mView != null){
            mView.dismissLoadingDialog();
        }
    }

    @Override
    public void getArticles(int size, int page) {
        if(mModel != null){
            mView.showLoadingDialog();
            mModel.getArticles(size, page);
        }
    }
}
