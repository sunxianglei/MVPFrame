package com.xianglei.mvpframe.base.inf;

/**
 * 显示隐藏加载框
 * @author sunxianglei
 * @date 2017/12/24
 */

public interface ILoadingView extends IView {
    void showLoadingDialog();
    void dismissLoadingDialog();
}
