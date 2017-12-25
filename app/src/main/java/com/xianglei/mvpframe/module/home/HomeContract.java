package com.xianglei.mvpframe.module.home;

import com.xianglei.mvpframe.base.inf.ILoadingView;
import com.xianglei.mvpframe.base.inf.IModel;
import com.xianglei.mvpframe.base.inf.IPresenter;
import com.xianglei.mvpframe.base.inf.IView;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public interface HomeContract {

    interface View extends ILoadingView {
        void setArticleList();
    }

    interface Model extends IModel {
        void getArticles();
    }

    interface Presenter extends IPresenter {
        void getArticles();
    }
}
