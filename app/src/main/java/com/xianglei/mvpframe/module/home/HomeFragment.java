package com.xianglei.mvpframe.module.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.BaseFragment;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.utils.Logger;
import com.xianglei.mvpframe.utils.PrintObject;

import java.util.List;

import butterknife.BindView;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, OnRefreshListener, OnLoadmoreListener{

    private static final String TAG = "HomeFragment";
    private static int SIZE = 10;
    private static int PAGE = 1;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private HomeContract.Presenter mHomePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadmoreListener(this);
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
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        Logger.d(TAG, PrintObject.toString(articleList.get(0)));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        PAGE = 1;
        mHomePresenter.getArticles(SIZE,PAGE);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mHomePresenter.getArticles(SIZE,++PAGE);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mHomePresenter.getArticles(SIZE,PAGE);
//    }
}
