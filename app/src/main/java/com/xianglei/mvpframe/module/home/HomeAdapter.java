package com.xianglei.mvpframe.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.data.bean.ArticleInfo;

import java.util.List;

import butterknife.BindView;
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

    public HomeAdapter(Context context){
        this.context = context;
    }

    public HomeAdapter(List<ArticleInfo> articleInfos, Context context) {
        this.articleInfos = articleInfos;
        this.context = context;
    }

    public void setData(List<ArticleInfo> articleInfos){
        this.articleInfos = articleInfos;
        this.notifyDataSetChanged();
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
            ((NormalHolder) holder).contentTV.setText(articleInfos.get(position).getDesc());
            ((NormalHolder) holder).domainTV.setText(articleInfos.get(position).getType());
            ((NormalHolder) holder).authorTV.setText(articleInfos.get(position).getWho());
            ((NormalHolder) holder).timeTV.setText(articleInfos.get(position).getPublishedAt());
//            ((NormalHolder) holder).iconIV.setBackground();
        }else if(holder instanceof RightImgHolder){
            ((RightImgHolder) holder).contentTV.setText(articleInfos.get(position).getDesc());
            ((RightImgHolder) holder).domainTV.setText(articleInfos.get(position).getType());
            ((RightImgHolder) holder).authorTV.setText(articleInfos.get(position).getWho());
            Glide.with(context)
                    .load(articleInfos.get(position).getImages().get(0))
                    .into(((RightImgHolder) holder).iconIV);
        }else if(holder instanceof TopImgHolder){
            ((TopImgHolder) holder).contentTV.setText(articleInfos.get(position).getDesc());
            ((TopImgHolder) holder).domainTV.setText(articleInfos.get(position).getType());
            ((TopImgHolder) holder).authorTV.setText(articleInfos.get(position).getWho());
            Glide.with(context)
                    .load(articleInfos.get(position).getImages().get(0))
                    .into(((TopImgHolder) holder).iconIV);
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

        @BindView(R.id.tv_content)
        TextView contentTV;
        @BindView(R.id.tv_domain)
        TextView domainTV;
        @BindView(R.id.tv_author)
        TextView authorTV;
        @BindView(R.id.tv_time)
        TextView timeTV;
        @BindView(R.id.icon)
        ImageView iconIV;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class RightImgHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content)
        TextView contentTV;
        @BindView(R.id.tv_domain)
        TextView domainTV;
        @BindView(R.id.tv_author)
        TextView authorTV;
        @BindView(R.id.icon)
        ImageView iconIV;

        public RightImgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class TopImgHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content)
        TextView contentTV;
        @BindView(R.id.tv_domain)
        TextView domainTV;
        @BindView(R.id.tv_author)
        TextView authorTV;
        @BindView(R.id.icon)
        ImageView iconIV;

        public TopImgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
