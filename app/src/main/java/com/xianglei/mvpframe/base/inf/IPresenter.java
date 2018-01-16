package com.xianglei.mvpframe.base.inf;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public interface IPresenter<V extends IView> {

    void attachView(V view);
    void detachView();

}
