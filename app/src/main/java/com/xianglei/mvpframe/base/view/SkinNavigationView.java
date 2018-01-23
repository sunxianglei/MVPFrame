package com.xianglei.mvpframe.base.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.R;
import android.util.AttributeSet;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;


/**
 * @author sunxianglei
 * @date 2018/1/23
 */

public class SkinNavigationView extends NavigationView implements SkinCompatSupportable {

    private static final int INVALID_ID = -1;

    private int mItemTextColor = INVALID_ID;
    private int mItemIconTint = INVALID_ID;
    private int mItemBackGround = INVALID_ID;

    public SkinNavigationView(Context context) {
        this(context, null);
    }

    public SkinNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NavigationView);
        mItemTextColor = ta.getResourceId(R.styleable.NavigationView_itemTextColor, INVALID_ID);
        mItemIconTint = ta.getResourceId(R.styleable.NavigationView_itemIconTint, INVALID_ID);
        mItemBackGround = ta.getResourceId(R.styleable.NavigationView_itemBackground, INVALID_ID);
        applyItemIconTint();
        applyItemTextColor();
        applyItemBackGround();
    }

    private void applyItemTextColor() {
        mItemTextColor = SkinCompatHelper.checkResourceId(mItemTextColor);
        if (mItemTextColor != INVALID_ID) {
            ColorStateList color = SkinCompatResources.getColorStateList(getContext(), mItemTextColor);
            setItemTextColor(color);
        }
    }

    private void applyItemIconTint() {
        mItemIconTint = SkinCompatHelper.checkResourceId(mItemIconTint);
        if (mItemIconTint != INVALID_ID) {
            ColorStateList color = SkinCompatResources.getColorStateList(getContext(), mItemIconTint);
            setItemIconTintList(color);
        }
    }

    private void applyItemBackGround() {
        mItemBackGround = SkinCompatHelper.checkResourceId(mItemBackGround);
        if (mItemBackGround != INVALID_ID) {
            int color = SkinCompatResources.getColor(getContext(), mItemBackGround);
            Drawable drawable = SkinCompatResources.getDrawable(getContext(), mItemBackGround);
            setItemBackground(drawable);
            setBackgroundColor(color);
        }
    }

    @Override
    public void applySkin() {
        applyItemTextColor();
        applyItemIconTint();
        applyItemBackGround();
    }
}
