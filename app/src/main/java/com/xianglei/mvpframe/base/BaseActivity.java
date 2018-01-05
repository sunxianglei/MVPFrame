package com.xianglei.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initViews(savedInstanceState);
        initParams();
    }

    /**
     * 设置布局资源文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化对象
     */
    protected abstract void initParams();

    /**
     * 回收资源
     */
    protected abstract void recycleRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        recycleRes();
    }
}
