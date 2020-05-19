package com.cpic.team.basetools.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cpic.team.basetools.R;
import com.qmuiteam.qmui.util.QMUIWindowInsetHelper;
import com.qmuiteam.qmui.widget.IWindowInsetLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by Jianxin on 2019/7/19.
 */

public class NormalTitleLayout extends LinearLayout implements IWindowInsetLayout {
    protected QMUIWindowInsetHelper mQMUIWindowInsetHelper;

    public QMUITopBarLayout getTopBar() {
        return topBar;
    }

    private QMUITopBarLayout topBar;

    public NormalTitleLayout(Context context) {
        super(context);
    }

    public NormalTitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mQMUIWindowInsetHelper = new QMUIWindowInsetHelper(this, this);
        LayoutInflater mInflater = LayoutInflater.from(context);
        topBar = (QMUITopBarLayout) mInflater.inflate(R.layout.layout_title, null);
        addView(topBar,0);
        topBar.setBackgroundDividerEnabled(false);
        setOrientation(VERTICAL);
    }

    public NormalTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mQMUIWindowInsetHelper = new QMUIWindowInsetHelper(this, this);
        LayoutInflater mInflater = LayoutInflater.from(context);
        topBar = (QMUITopBarLayout) mInflater.inflate(R.layout.layout_title, null);
        addView(topBar,0);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewCompat.requestApplyInsets(this);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // xiaomi 8 not reapply insets default...
        ViewCompat.requestApplyInsets(this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override
    public boolean applySystemWindowInsets19(Rect insets) {
        return mQMUIWindowInsetHelper.defaultApplySystemWindowInsets19(this, insets);
    }

    @Override
    public boolean applySystemWindowInsets21(Object insets) {
        return mQMUIWindowInsetHelper.defaultApplySystemWindowInsets21(this, insets);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            return applySystemWindowInsets19(insets);
        }
        return super.fitSystemWindows(insets);
    }
}
