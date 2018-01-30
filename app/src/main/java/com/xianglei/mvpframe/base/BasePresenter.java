package com.xianglei.mvpframe.base;

import com.xianglei.mvpframe.base.inf.IModel;
import com.xianglei.mvpframe.base.inf.IPresenter;
import com.xianglei.mvpframe.base.inf.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author sunxianglei
 * @date 2018/1/16
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    private Reference<V> mViewRef;  //View接口类型的弱引用
    private M mModel;

    /**
     * 绑定View
     * @param view
     */
    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);  //建立关联
        mModel = createModel();
    }

    /**
     * 解绑view、取消请求
     */
    @Override
    public void detachView(){
        if(mViewRef != null) {
            mViewRef.clear();   //解除关联
            mViewRef = null;
        }
        mModel = null;
    }

    public V getView(){
        return mViewRef.get();
    }

    public M getModel(){
        return mModel;
    }

    public abstract M createModel();

}
