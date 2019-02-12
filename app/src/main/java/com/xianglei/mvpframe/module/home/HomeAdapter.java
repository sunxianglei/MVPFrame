package com.xianglei.mvpframe.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianglei.mvpframe.GlideApp;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.inf.OnItemClickListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.utils.Const;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sunxianglei
 * @date 2018/1/5
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_RIGHTIMG = 1;
    private static final int ITEM_TYPE_TOPIMG = 2;

    private List<ArticleInfo> articleInfos;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

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

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ITEM_TYPE_RIGHTIMG == viewType) {
            return new RightImgHolder(LayoutInflater.from(context).inflate(R.layout.item_home_rightimg, parent, false));
        } else if (ITEM_TYPE_TOPIMG == viewType) {
            return new TopImgHolder(LayoutInflater.from(context).inflate(R.layout.item_home_topimg, parent, false));
        } else if(ITEM_TYPE_NORMAL == viewType){
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.item_home_normal, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(articleInfos == null){
            return;
        }
        if(holder instanceof NormalHolder){
            ((NormalHolder) holder).contentTV.setText(articleInfos.get(position).getDesc());
            ((NormalHolder) holder).domainTV.setText(articleInfos.get(position).getType());
            ((NormalHolder) holder).authorTV.setText(articleInfos.get(position).getWho());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ((NormalHolder) holder).timeTV.setText(sdf.format(articleInfos.get(position).getPublishedAt()));
            GlideApp.with(context)
                    .load(getDrawableRes(articleInfos.get(position).getType()))
                    .into(((NormalHolder) holder).iconIV);
        }else if(holder instanceof RightImgHolder){
            ((RightImgHolder) holder).contentTV.setText(articleInfos.get(position).getDesc());
            ((RightImgHolder) holder).domainTV.setText(articleInfos.get(position).getType());
            ((RightImgHolder) holder).authorTV.setText(articleInfos.get(position).getWho());
            GlideApp.with(context)
                    .load(articleInfos.get(position).getImages().get(0))
                    .centerCrop()
                    .placeholder(R.drawable.default_icon)
                    .error(R.drawable.default_icon)
                    .into(((RightImgHolder) holder).iconIV);
        }else if(holder instanceof TopImgHolder){
            ((TopImgHolder) holder).contentTV.setText(articleInfos.get(position).getDesc());
            ((TopImgHolder) holder).domainTV.setText(articleInfos.get(position).getType());
            ((TopImgHolder) holder).authorTV.setText(articleInfos.get(position).getWho());
            GlideApp.with(context)
                    .load(articleInfos.get(position).getImages().get(0))
                    .centerCrop()
                    .placeholder(R.drawable.default_icon)
                    .error(R.drawable.default_icon)
                    .into(((TopImgHolder) holder).iconIV);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null) mOnItemClickListener.onItemClick(view, position);
            }
        });
    }

    public int getDrawableRes(String type){
        int drawble = R.drawable.ic_menu_android_24dp;
        switch (type){
            case Const.ALL:
                drawble = R.drawable.ic_menu_all;
                break;
            case Const.ANDROID:
                drawble = R.drawable.ic_menu_android_24dp;
                break;
            case Const.IOS:
                drawble = R.drawable.ic_menu_apple;
                break;
            case Const.FULI:
                drawble = R.drawable.ic_menu_mood_24dp;
                break;
            case Const.QIANDUAN:
                drawble = R.drawable.ic_menu_qianduan;
                break;
            case Const.SHIPIN:
                drawble = R.drawable.ic_menu_video;
                break;
            case Const.TUIJIAN:
                drawble = R.drawable.ic_menu_more;
                break;
            case Const.TUOZHAN:
                drawble = R.drawable.ic_menu_tuozhan;
                break;
            default:
                break;
        }
        return drawble;
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
