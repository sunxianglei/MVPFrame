package com.xianglei.mvpframe.base.inf;

/**
 * 最基本的Model与Presenter回调接口
 * @author sunxianglei
 * @date 2017/12/24
 */

public interface ICallBackListener<T> {
    void onSuccess(T data);
    void onFailure();
}
