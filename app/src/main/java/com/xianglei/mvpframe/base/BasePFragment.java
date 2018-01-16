package com.xianglei.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.xianglei.mvpframe.base.inf.IPresenter;
import com.xianglei.mvpframe.base.inf.IView;


/**
 * 基于Presenter的Fragment基类
 * @author sunxianglei
 * @date 2018/1/16
 */

public abstract class BasePFragment<V extends IView, P extends IPresenter> extends BaseFragment{

    private P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mPresenter == null){
            mPresenter = createPresenter();
            mPresenter.attachView(bindView());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.detachView();
        }
        mPresenter = null;
    }

    public P getPresenter(){
        return mPresenter;
    }

    /**
     * 绑定View
     * @return
     */
    public abstract V bindView();

    /**
     * 创建presenter
     * @return
     */
    public abstract P createPresenter();
}
