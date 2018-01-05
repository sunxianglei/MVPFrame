package com.xianglei.mvpframe.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.data.bean.ArticleInfo;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author sunxianglei
 * @date 2018/1/5
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_RIGHTIMG = 1;
    private static final int ITEM_TYPE_TOPIMG = 2;

    private List<ArticleInfo> articleInfos;
    private Context context;

    public HomeAdapter(List<ArticleInfo> articleInfos, Context context) {
        this.articleInfos = articleInfos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ITEM_TYPE_RIGHTIMG == viewType) {
            return new RightImgHolder(LayoutInflater.from(context).inflate(R.layout.item_home_rightimg, parent, false));
        } else if (ITEM_TYPE_TOPIMG == viewType) {
            return new TopImgHolder(LayoutInflater.from(context).inflate(R.layout.item_home_topimg, parent, false));
        } else {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.item_home_normal, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalHolder){

        }else if(holder instanceof RightImgHolder){

        }else if(holder instanceof TopImgHolder){

        }
    }

    @Override
    public int getItemCount() {
        return articleInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (articleInfos.get(position).getImages() != null) {
            if (position % 2 == 0) {
                return ITEM_TYPE_RIGHTIMG;
            } else {
                return ITEM_TYPE_TOPIMG;
            }
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class RightImgHolder extends RecyclerView.ViewHolder {

        public RightImgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class TopImgHolder extends RecyclerView.ViewHolder {

        public TopImgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
