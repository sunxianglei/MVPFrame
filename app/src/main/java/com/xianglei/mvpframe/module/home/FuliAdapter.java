package com.xianglei.mvpframe.module.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xianglei.mvpframe.GlideApp;
import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.inf.OnItemClickListener;
import com.xianglei.mvpframe.data.bean.ArticleInfo;
import com.xianglei.mvpframe.utils.DisplayUtil;
import com.xianglei.mvpframe.utils.MathUtil;

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
    private OnItemClickListener mOnItemClickListener;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = ((FuliHolder)holder).fuliIV.getLayoutParams();
        lp.height = DisplayUtil.dip2px(context, MathUtil.randomInt(130,260));
        GlideApp.with(context)
                .load(articleInfos.get(position).getUrl())
                .centerCrop()
                .placeholder(R.drawable.default_icon)
                .error(R.drawable.default_icon)
                .into(((FuliHolder)holder).fuliIV);
        ((FuliHolder)holder).fuliIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null) mOnItemClickListener.onItemClick(view, position);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
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
