package com.xianglei.mvpframe.module.home;

import com.xianglei.mvpframe.base.inf.ILoadingView;
import com.xianglei.mvpframe.base.inf.IModel;
import com.xianglei.mvpframe.base.inf.IPresenter;
import com.xianglei.mvpframe.base.inf.IView;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;

import java.util.List;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public interface HomeContract {

    interface View extends ILoadingView {
        void setArticleList(List<ArticleInfo> articleList);
    }

    interface Model extends IModel {
        void getArticles(int size, int page);
    }

    interface Presenter extends IPresenter {
        void getArticles(int size, int page);
    }
}
