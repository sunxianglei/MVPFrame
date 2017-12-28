package com.xianglei.mvpframe.data.bean;

/**
 * 公用返回数据模型，这个模型代表有error和results字段
 * {
 *  error:
 *  results:{}
 * }
 * 如果有不同的返回格式（多了一些字段），可继承这个类来扩展
 * @author sunxianglei
 * @date 2017/12/25
 */

public class CommonBean<T> {

    private Boolean error;
    private T results;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
