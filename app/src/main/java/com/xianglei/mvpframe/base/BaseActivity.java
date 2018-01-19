package com.xianglei.mvpframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xianglei.mvpframe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author sunxianglei
 * @date 2017/12/24
 */

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initToolBar();
        initViews(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initParams();
    }

    private void initToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public Toolbar getToolbar(){
        return toolbar;
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
     * 对象操作（如进入页面第一次请求等）
     */
    protected abstract void initParams();

    /**
     * 回收资源
     */
    protected abstract void recycleRes();

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        recycleRes();
        super.onDestroy();
    }
}
