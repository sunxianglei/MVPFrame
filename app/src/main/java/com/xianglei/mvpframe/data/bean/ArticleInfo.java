package com.xianglei.mvpframe.data.bean;

import java.util.List;

/**
 * @author sunxianglei
 * @date 2017/12/25
 */

public class ArticleInfo {

    /**
     * createdAt : 2017-12-21T16:10:30.556Z
     * publishedAt : 2017-12-25T08:28:04.64Z
     * _id : 5a3b6c76421aa90fef2035b7
     * source : web
     * used : true
     * type : Android
     * url : http://rkhcy.github.io/2017/12/21/%E5%9B%BE%E8%A7%A3RxJava2(%E4%BA%8C)/
     * desc : 图解RxJava2(二)
     * who : HuYounger
     */
    private String createdAt;
    private String publishedAt;
    private String _id;
    private String source;
    private boolean used;
    private String type;
    private String url;
    private String desc;
    private String who;
    private List<String> images;

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String get_id() {
        return _id;
    }

    public String getSource() {
        return source;
    }

    public boolean isUsed() {
        return used;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    public String getWho() {
        return who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
