package com.xianglei.mvpframe.module.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.BasePFragment;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.utils.Constant;
import com.xianglei.mvpframe.utils.Logger;
import com.xianglei.mvpframe.utils.PrintObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomeFragment extends BasePFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View,
        OnRefreshListener, OnLoadmoreListener{

    private static final String TAG = "HomeFragment";
    private static final String TYPE = "type";
    private static int SIZE = 10;
    private static int PAGE = 1;
    private String mType = Constant.ANDROID;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private HomeAdapter mHomeAdapter;

    public static HomeFragment newInstance(String str){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, str);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public HomeContract.View bindView() {
        return this;
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

        mType = getArguments().getString(TYPE);
        if(Constant.FULI == mType){

        }else{
            mHomeAdapter = new HomeAdapter(new ArrayList<ArticleInfo>(), getContext());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mHomeAdapter);
        }
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadmoreListener(this);

    }

    @Override
    protected void initParams() {
        getPresenter().getArticles(mType, SIZE, PAGE);
    }

    @Override
    protected void recycleRes() {
        mHomeAdapter = null;
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
        mHomeAdapter.setData(articleList);
        Logger.d(TAG, PrintObject.toString(articleList.get(0)));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        PAGE = 1;
        getPresenter().getArticles(mType, SIZE,PAGE);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getPresenter().getArticles(mType, SIZE,++PAGE);
    }

}
