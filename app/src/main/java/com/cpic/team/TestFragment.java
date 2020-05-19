package com.cpic.team;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.cpic.team.baselib.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jianxin on 2019/7/29.
 */

public class TestFragment extends Fragment {
    @BindView(R.id.topBar)
    QMUITopBarLayout topBar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_test, null);
        ButterKnife.bind(this, view);
        topBar.setBackgroundAlpha(0);
        topBar.setTitle("标题啊");
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                topBar.setBackgroundAlpha(scrollY);
            }
        });
        topBar.post(new Runnable() {
            @Override
            public void run() {
                topBar.setPadding(0, QMUIStatusBarHelper.getStatusbarHeight(getActivity()), 0, 0);
            }
        });
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            QMUIStatusBarHelper.setStatusBarLightMode(getActivity());
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
