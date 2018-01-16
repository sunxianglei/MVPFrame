package com.xianglei.mvpframe.base;

import com.xianglei.mvpframe.base.inf.IModel;
import com.xianglei.mvpframe.base.inf.IPresenter;
import com.xianglei.mvpframe.base.inf.IView;

/**
 * @author sunxianglei
 * @date 2018/1/16
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    private V mView;
    private M mModel;

    /**
     * 绑定View
     * @param view
     */
    @Override
    public void attachView(V view) {
        this.mView = view;
        mModel = createModel();
    }

    /**
     * 解绑view、取消请求
     */
    @Override
    public void detachView(){
        mView = null;
        mModel = null;
    }

    public V getView(){
        return mView;
    }

    public M getModel(){
        return mModel;
    }

    public abstract M createModel();

}
