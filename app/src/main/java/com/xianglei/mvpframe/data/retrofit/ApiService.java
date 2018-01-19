package com.xianglei.mvpframe.data.retrofit;

import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.data.bean.CommonBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author sunxianglei
 * @date 2017/12/24
 * @date 2017/12/24
 */

public interface ApiService {

    @GET("{type}/{size}/{page}")
    Observable<CommonBean<List<ArticleInfo>>> getArticles(@Path("type") String type, @Path("size") int size, @Path("page") int page);

}
