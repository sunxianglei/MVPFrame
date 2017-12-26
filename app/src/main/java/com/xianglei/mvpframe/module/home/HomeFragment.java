package com.xianglei.mvpframe.module.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.BaseFragment;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;
import com.xianglei.mvpframe.utils.Logger;

import java.util.List;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomeFragment extends BaseFragment implements HomeContract.View{

    private static final String TAG = "HomeFragment";
    private static int SIZE = 10;
    private static int PAGE = 1;

    private HomeContract.Presenter mHomePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initParams() {
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.getArticles(SIZE,PAGE);
    }

    @Override
    protected void recycleRes() {
        mHomePresenter = null;
    }

    @Override
    public void showLoadingDialog() {
        Logger.d(TAG, "showLoadingDialog");
    }

    @Override
    public void dismissLoadingDialog() {
        Logger.d(TAG, "dismissLoadingDialog");
    }

    @Override
    public void setArticleList(List<ArticleInfo> articleList) {
        Logger.d(TAG, "dismissLoadingDialog");
    }
}
