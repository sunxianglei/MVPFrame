package com.xianglei.mvpframe.module.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xianglei.mvpframe.GlideApp;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.BasePFragment;
import com.xianglei.mvpframe.base.inf.OnItemClickListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.module.detail.DetailActivity;
import com.xianglei.mvpframe.utils.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public class HomeFragment extends BasePFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View,
        OnRefreshListener, OnLoadmoreListener, OnItemClickListener{

    private static final String TAG = "HomeFragment";
    private static final String TYPE = "type";
    private static int SIZE = 10;
    private static int PAGE = 1;
    private String mType = Const.ANDROID;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.expanded_image)
    ImageView expandedIV;
    @BindView(R.id.container)
    FrameLayout mContainer;

    private HomeAdapter mHomeAdapter;
    private FuliAdapter mFuliAdapter;
    private List<ArticleInfo> articleInfos;

    private int mShortAnimationDuration = 300;
    private Animator mCurrentAnimator;

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
        if(Const.FULI == mType){
            mFuliAdapter = new FuliAdapter(new ArrayList<ArticleInfo>(), getContext());
            mFuliAdapter.setOnItemClickListener(this);
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setAdapter(mFuliAdapter);
        }else{
            mHomeAdapter = new HomeAdapter(new ArrayList<ArticleInfo>(), getContext());
            mHomeAdapter.setOnItemClickListener(this);
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
        mFuliAdapter = null;
    }

    @Override
    public void setArticleList(List<ArticleInfo> articleList) {
        if(Const.FULI == mType){
            mFuliAdapter.setData(articleList);
        }else {
            mHomeAdapter.setData(articleList);
        }
        articleInfos = articleList;
        LoadingFinish();
    }

    @Override
    public void requestFailure() {
        LoadingFinish();
        Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        PAGE = 1;
        getPresenter().getArticles(mType, SIZE, PAGE);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getPresenter().getArticles(mType, SIZE, ++PAGE);
    }

    private void LoadingFinish(){
        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.finishRefresh();
        }
        if(mRefreshLayout.isLoading()){
            mRefreshLayout.finishLoadmore();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if(Const.FULI == mType){
            zoomImageFromThumb((ImageView) view, articleInfos.get(position).getUrl());
        }else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(Const.URL, articleInfos.get(position).getUrl());
            startActivity(intent);
        }
    }

    /**
     * 缩放图片
     * from google develop
     * @param thumbView
     * @param imageRes
     */
    private void zoomImageFromThumb(final ImageView thumbView, String imageRes) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        expandedIV.setImageDrawable(thumbView.getDrawable());
        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        mContainer.getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedIV.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedIV.setPivotX(0f);
        expandedIV.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(expandedIV, View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedIV, View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedIV, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedIV, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                expandedIV.setBackgroundColor(Color.BLACK);
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(expandedIV, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedIV, View.Y,startBounds.top))
                        .with(ObjectAnimator.ofFloat(expandedIV, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator.ofFloat(expandedIV, View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedIV.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedIV.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
