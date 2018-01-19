package com.xianglei.mvpframe.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.data.bean.ArticleInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sunxianglei
 * @date 2018/1/19
 */

public class FuliAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ArticleInfo> articleInfos;
    private Context context;

    public FuliAdapter(List<ArticleInfo> articleInfos, Context context) {
        this.articleInfos = articleInfos;
        this.context = context;
    }

    public void setData(List<ArticleInfo> articleInfos){
        this.articleInfos = articleInfos;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FuliHolder(LayoutInflater.from(context).inflate(R.layout.item_fuli, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context)
                .load(articleInfos.get(position).getUrl())
                .into(((FuliHolder)holder).fuliIV);
    }

    @Override
    public int getItemCount() {
        return articleInfos.size();
    }

    public class FuliHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_fuli)
        ImageView fuliIV;

        public FuliHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
