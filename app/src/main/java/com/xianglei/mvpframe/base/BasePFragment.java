package com.xianglei.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianglei.mvpframe.base.inf.IPresenter;
import com.xianglei.mvpframe.base.inf.IView;


/**
 * 基于Presenter的Fragment基类
 * @author sunxianglei
 * @date 2018/1/16
 */

public abstract class BasePFragment<V extends IView, P extends IPresenter> extends BaseFragment{

    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mPresenter == null){
            mPresenter = createPresenter();
            mPresenter.attachView(bindView());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter != null){
            mPresenter.detachView();
        }
        mPresenter = null;
        super.onDestroyView();
    }

    public P getPresenter(){
        if(mPresenter == null) throw new NullPointerException("presenter不能为空！");
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
