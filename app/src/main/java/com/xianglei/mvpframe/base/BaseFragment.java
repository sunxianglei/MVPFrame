package com.xianglei.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initViews(view, savedInstanceState);
        initParams();
        return view;
    }

    /**
     * 设置布局资源文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initViews(View view, Bundle savedInstanceState);

    /**
     * 初始化对象
     */
    protected abstract void initParams();

    /**
     * 回收资源
     */
    protected abstract void recycleRes();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recycleRes();
    }
}
